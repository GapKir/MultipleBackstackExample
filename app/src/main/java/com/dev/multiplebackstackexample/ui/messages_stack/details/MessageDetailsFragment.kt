package com.dev.multiplebackstackexample.ui.messages_stack.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentMessageDetailsBinding
import kotlinx.coroutines.launch

class MessageDetailsFragment : Fragment(R.layout.fragment_message_details) {

    private var _binding: FragmentMessageDetailsBinding? = null
    private val binding: FragmentMessageDetailsBinding get() = _binding!!
    private val viewModel: MessageDetailsViewModel by viewModels()
    private val args by navArgs<MessageDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMessageDetailsBinding.bind(view)

        viewModel.showMessage(args.messageId)
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
}