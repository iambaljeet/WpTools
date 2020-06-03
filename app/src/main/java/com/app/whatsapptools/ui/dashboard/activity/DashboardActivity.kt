package com.app.whatsapptools.ui.dashboard.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.whatsapptools.R
import com.app.whatsapptools.utility.applyTopInsets
import kotlinx.android.synthetic.main.dashboard_activity.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        container.applyTopInsets()

        bottom_bar_dashboard.setupWithNavController(findNavController(R.id.fragment_nav))
    }
}