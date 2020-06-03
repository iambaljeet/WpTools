package com.app.whatsapptools.database.entitity

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessagesTextEntity(
    @PrimaryKey var messageText: String,
    @ColumnInfo(name = "countryCode") var countryCode: String,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String,
    @ColumnInfo(name = "dateTime") var dateTime: Long
)

class DiffUtilMessagesTextEntity: DiffUtil.ItemCallback<MessagesTextEntity>() {
    override fun areItemsTheSame(
        oldItem: MessagesTextEntity,
        newItem: MessagesTextEntity
    ): Boolean {
        return newItem.messageText == oldItem.messageText
    }

    override fun areContentsTheSame(
        oldItem: MessagesTextEntity,
        newItem: MessagesTextEntity
    ): Boolean {
        return newItem == oldItem
    }
}