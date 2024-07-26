package com.dev.multiplebackstackexample.ui.profile_stack

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentEditProfileBinding

class EditProfileFragment: Fragment(R.layout.fragment_edit_profile) {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding: FragmentEditProfileBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditProfileBinding.bind(view)

        arguments?.getString(KEY_EDITED_NAME)?.let {
            binding.editProfileEtEditName.setText(it)
        }

        binding.editProfileBtnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.editProfileBtnConfirm.setOnClickListener {
            val editedName = binding.editProfileEtEditName.text.toString()
            parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(KEY_EDITED_NAME to editedName))
            parentFragmentManager.popBackStack()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_EDITED_NAME = "KEY_EDITED_NAME"
        const val REQUEST_CODE = "REQUEST_CODE"

        fun newInstance(name: String): EditProfileFragment {
            val fragment = EditProfileFragment()
            fragment.arguments = bundleOf(KEY_EDITED_NAME to name)
            return fragment
        }
    }
}