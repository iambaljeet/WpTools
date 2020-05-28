package com.app.whatsapptools.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.whatsapptools.database.dao.MessageDao
import com.app.whatsapptools.database.entitity.MessagesTextEntity

@Database(entities = [MessagesTextEntity::class], version = 1)
abstract class MessagesDatabase: RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile private var instance: MessagesDatabase? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            MessagesDatabase::class.java, "messages.db")
            .build()
    }
}