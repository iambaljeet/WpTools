package com.app.whatsapptools.database.entitity

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