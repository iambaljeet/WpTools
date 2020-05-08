package com.app.whatsapptools.app

import android.app.Application

class WpToolsApp: Application() {

    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            // Android context
//            androidContext(this@WpToolsApp)
//            // modules
//            modules(dashboardViewModelModule)
//        }
    }
}