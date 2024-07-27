package com.dev.multiplebackstackexample

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.databinding.ActivityMainBinding
import com.dev.multiplebackstackexample.ui.home_stack.HomeFragment
import com.dev.multiplebackstackexample.ui.messages_stack.main.MessagesFragment
import com.dev.multiplebackstackexample.ui.profile_stack.ProfileFragment
import com.dev.multiplebackstackexample.utills.Stack

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private var curStack = Stack.HOME
    private var homeStackExist = false
    private var messagesStackExist = false
    private var profileStackExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        firstOpenTab(HomeFragment.newInstance(), Stack.HOME)
        initBottomNavView()
        handleBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initBottomNavView() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    if (homeStackExist) {
                        restoreStackFragment(Stack.HOME)
                    } else {
                        firstOpenTab(HomeFragment.newInstance(), Stack.HOME)
                    }
                }

                R.id.messages -> {
                    if (messagesStackExist) {
                        restoreStackFragment(Stack.MESSAGES)
                    } else {
                        firstOpenTab(MessagesFragment.newInstance(), Stack.MESSAGES)
                    }
                }

                R.id.profile -> {
                    if (profileStackExist) {
                        restoreStackFragment(Stack.PROFILE)
                    } else {
                        firstOpenTab(ProfileFragment.newInstance(), Stack.PROFILE)
                    }
                }

                else -> Unit
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun firstOpenTab(fragment: Fragment, stack: Stack) {
        //Save all previous backStack and close it
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.saveBackStack(supportFragmentManager.getBackStackEntryAt(i).name!!)
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_container, fragment, fragment.javaClass.name)
            setReorderingAllowed(true)
            addToBackStack(stack.name)
            commit()
        }
        curStack = stack
        when (stack) {
            Stack.HOME -> homeStackExist = true
            Stack.MESSAGES -> messagesStackExist = true
            Stack.PROFILE -> profileStackExist = true
        }
    }

    private fun restoreStackFragment(stack: Stack) {
        supportFragmentManager.saveBackStack(curStack.name)
        curStack = stack
        supportFragmentManager.restoreBackStack(stack.name)
    }


    private fun handleBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount == 1) {
                    finish()
                } else {
                    supportFragmentManager.popBackStack()
                }
            }
        })
    }

}