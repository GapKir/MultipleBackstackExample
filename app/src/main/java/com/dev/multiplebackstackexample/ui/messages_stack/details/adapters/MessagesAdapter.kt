package com.dev.multiplebackstackexample.ui.messages_stack.details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dev.multiplebackstackexample.databinding.MessageItemBinding
import com.dev.multiplebackstackexample.model.Message

class MessagesAdapter(
    private val listener: Listener
) : ListAdapter<Message, MessagesAdapter.MessageHolder>(ItemCallback), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MessageItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return MessageHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = getItem(position)

        with(holder.binding){
            root.tag = message
            messageItemTvPerson.text = message.person
            messageItemTvSubject.text = message.subject
        }
    }

    override fun onClick(v: View?) {
        val message = v?.tag as Message
        listener.openMessage(message.id)
    }

    inner class MessageHolder(
        val binding: MessageItemBinding
    ) : ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

    }

    interface Listener {
        fun openMessage(messageId: Long) }
}