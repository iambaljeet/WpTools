package com.app.whatsapptools.repository

import androidx.lifecycle.LiveData
import com.app.whatsapptools.app.WpToolsApp
import com.app.whatsapptools.database.entitity.MessagesTextEntity

object MessagesHistoryRepository {
    private val messageDao = WpToolsApp.database.messageDao()

    fun getPastMessages(): LiveData<MutableList<MessagesTextEntity>> {
        return messageDao.getAllMessages()
    }

    fun addMessage(messagesTextEntity: MessagesTextEntity) {
        return messageDao.insertMessage(messagesTextEntity)
    }
}