package com.dev.multiplebackstackexample.ui.home_stack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentHomeBinding
import com.dev.multiplebackstackexample.utills.Stack
import com.dev.multiplebackstackexample.utills.openFragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.homeBtnGoToNextScreen.setOnClickListener {
            openFragment(SecondFragment.newInstance(), Stack.HOME)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}