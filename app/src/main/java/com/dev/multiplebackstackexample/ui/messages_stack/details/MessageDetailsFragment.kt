package com.dev.multiplebackstackexample.ui.messages_stack.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentMessageDetailsBinding
import kotlinx.coroutines.launch

class MessageDetailsFragment : Fragment(R.layout.fragment_message_details) {

    private var _binding: FragmentMessageDetailsBinding? = null
    private val binding: FragmentMessageDetailsBinding get() = _binding!!
    private val viewModel: MessageDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMessageDetailsBinding.bind(view)

        arguments?.getLong(KEY_ID_MESSAGE)?.let { viewModel.showMessage(it) }
        observeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUi() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.message.collect {
                    with(binding) {
                        messageDetailsTvFrom.text = it.person
                        messageDetailsTvSubject.text = it.subject
                        messageDetailsTvBody.text = it.body
                    }
                }
            }
        }
    }
    companion object {
        private const val KEY_ID_MESSAGE = "KEY_ID_MESSAGE"

        fun newInstance(arg: Long): MessageDetailsFragment {
            val fragment = MessageDetailsFragment()
            fragment.arguments = bundleOf(KEY_ID_MESSAGE to arg)
            return fragment
        }
    }
}