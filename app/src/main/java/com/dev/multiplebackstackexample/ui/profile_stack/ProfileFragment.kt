package com.dev.multiplebackstackexample.ui.profile_stack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentProfileBinding

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
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            EditProfileFragment.KEY_EDITED_NAME
        )?.observe(viewLifecycleOwner){
            binding.profileTvEditName.text = it
        }
    }

    private fun openEditScreenProfile() {
        val curName = binding.profileTvEditName.text.toString()
        val direction =
            ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(curName)
        findNavController().navigate(direction)
    }
}