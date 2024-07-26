package com.dev.multiplebackstackexample.ui.profile_stack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentProfileBinding
import com.dev.multiplebackstackexample.utills.Stack
import com.dev.multiplebackstackexample.utills.openFragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        listenResultFromEditFragment()
        binding.profileBtnEditName.setOnClickListener { openEditScreenProfile() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listenResultFromEditFragment() {
        parentFragmentManager.setFragmentResultListener(
            EditProfileFragment.REQUEST_CODE, viewLifecycleOwner
        ) { _, data ->
            binding.profileTvEditName.text = data.getString(EditProfileFragment.KEY_EDITED_NAME)
        }
    }

    private fun openEditScreenProfile() {
        val name = binding.profileTvEditName.text.toString()
        openFragment(EditProfileFragment.newInstance(name), Stack.PROFILE)
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}