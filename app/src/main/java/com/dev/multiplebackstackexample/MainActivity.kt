package com.dev.multiplebackstackexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.ui.home_stack.HomeFragment
import com.dev.multiplebackstackexample.ui.messages_stack.main.MessagesFragment
import com.dev.multiplebackstackexample.ui.profile_stack.ProfileFragment
import com.dev.multiplebackstackexample.utills.Stack
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var curStack = Stack.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(HomeFragment.newInstance(), Stack.HOME)
        initBottomNavView()

    }

    private fun initBottomNavView() {
        findViewById<BottomNavigationView>(R.id.bottom_nav).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> openFragment(HomeFragment.newInstance(), Stack.HOME)
                R.id.messages -> openFragment(MessagesFragment.newInstance(), Stack.MESSAGES)
                R.id.profile -> openFragment(ProfileFragment.newInstance(), Stack.PROFILE)
                else -> Unit
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun openFragment(fragment: Fragment, stack: Stack) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (fragmentManager.findFragmentByTag(stack.name) != null) {
            fragmentManager.saveBackStack(curStack.name)
            fragmentManager.restoreBackStack(stack.name)
            curStack = stack
        } else {
            curStack = stack
            fragmentTransaction.apply {
                replace(R.id.nav_host_container, fragment, stack.name)
                setReorderingAllowed(true)
                addToBackStack(stack.name)
                commit()
            }
        }
    }
}