package com.dev.multiplebackstackexample.utills

import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.R

fun Fragment.openFragment(fragment: Fragment, stack: Stack) {
    val fragmentManager = parentFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.apply {
        replace(R.id.nav_host_container, fragment, stack.name)
        setReorderingAllowed(true)
        addToBackStack(stack.name)
        commit()
    }
}