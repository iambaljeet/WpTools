package com.app.whatsapptools.app

import android.app.Application
import com.app.whatsapptools.database.database.MessagesDatabase

class WpToolsApp: Application() {
    companion object {
        lateinit var database: MessagesDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = MessagesDatabase.invoke(this)
    }
}