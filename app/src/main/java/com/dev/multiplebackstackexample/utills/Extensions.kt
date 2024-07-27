package com.dev.multiplebackstackexample.utills

import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.R

fun Fragment.openFragment(fragment: Fragment, stack: Stack) {
    parentFragmentManager.beginTransaction().apply {
        replace(R.id.nav_host_container, fragment)
        setReorderingAllowed(true)
        addToBackStack(stack.name)
        commit()
    }
}