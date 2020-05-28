package com.app.whatsapptools.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.whatsapptools.database.entitity.MessagesTextEntity

@Dao
interface MessageDao {
    @Query("SELECT * from MessagesTextEntity ORDER BY dateTime ASC")
    fun getAllMessages(): LiveData<MutableList<MessagesTextEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(vararg messagesTextEntity: MessagesTextEntity)
}