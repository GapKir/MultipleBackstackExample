package com.dev.multiplebackstackexample.ui.profile_stack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentEditProfileBinding

class EditProfileFragment: Fragment(R.layout.fragment_edit_profile) {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding: FragmentEditProfileBinding get() = _binding!!

    private val args by navArgs<EditProfileFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditProfileBinding.bind(view)

        binding.editProfileEtEditName.setText(args.curName)

        binding.editProfileBtnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.editProfileBtnConfirm.setOnClickListener {
            val editedName = binding.editProfileEtEditName.text.toString()
            findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_EDITED_NAME, editedName)
            findNavController().popBackStack()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_EDITED_NAME = "KEY_EDITED_NAME"
    }

}