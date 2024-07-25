package com.dev.multiplebackstackexample.ui.messages_stack.details

import androidx.lifecycle.ViewModel
import com.dev.multiplebackstackexample.model.Message
import com.dev.multiplebackstackexample.model.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MessageDetailsViewModel: ViewModel() {

    private val _message = MutableStateFlow(MessageRepository.messages.first())
    val message: StateFlow<Message> = _message.asStateFlow()

    fun showMessage(id: Long) {
        val messageToShow = MessageRepository.messages.first { it.id == id}
        _message.value = messageToShow
    }

}