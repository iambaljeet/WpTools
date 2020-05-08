package com.app.whatsapptools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.whatsapptools.ui.dashboard.activity.DashboardActivity
import com.app.whatsapptools.utility.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        launch<DashboardActivity> {}
    }
}