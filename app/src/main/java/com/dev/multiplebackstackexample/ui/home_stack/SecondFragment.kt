package com.dev.multiplebackstackexample.ui.home_stack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentSecondBinding

class SecondFragment: Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SecondFragment()
    }
}