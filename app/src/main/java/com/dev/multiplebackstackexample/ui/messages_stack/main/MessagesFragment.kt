package com.dev.multiplebackstackexample.ui.messages_stack.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dev.multiplebackstackexample.R
import com.dev.multiplebackstackexample.databinding.FragmentMessagesBinding
import com.dev.multiplebackstackexample.model.MessageRepository
import com.dev.multiplebackstackexample.ui.messages_stack.details.MessageDetailsFragment
import com.dev.multiplebackstackexample.ui.messages_stack.details.adapters.MessagesAdapter
import com.dev.multiplebackstackexample.utills.Stack
import com.dev.multiplebackstackexample.utills.openFragment

class MessagesFragment: Fragment(R.layout.fragment_messages), MessagesAdapter.Listener {

    private var _binding: FragmentMessagesBinding? = null
    private val binding: FragmentMessagesBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMessagesBinding.bind(view)

        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openMessage(messageId: Long) {
        openFragment(MessageDetailsFragment.newInstance(messageId), Stack.MESSAGES)
    }

    private fun initAdapter() {
       val adapter = MessagesAdapter(this)
        binding.root.adapter = adapter
        adapter.submitList(MessageRepository.messages)
    }

    companion object {
        fun newInstance() = MessagesFragment()
    }
}