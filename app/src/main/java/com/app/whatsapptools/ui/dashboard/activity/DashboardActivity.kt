package com.app.whatsapptools.ui.dashboard.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.app.whatsapptools.R
import com.app.whatsapptools.ui.dashboard.viewmodel.DashboardViewModel
import com.app.whatsapptools.utility.applyTopInsets
import kotlinx.android.synthetic.main.dashboard_activity.*

class DashboardActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener
    , NavController.OnDestinationChangedListener {
    val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        container.applyTopInsets(40)

        findNavController(R.id.fragment_nav).addOnDestinationChangedListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.item_home -> {
                Toast.makeText(this, "Menu item clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.item_repeat -> {
            }
            R.id.item_no_number -> {
            }
        }
        return false
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id) {
            R.id.historyFragment -> {
            }
            else -> {
            }
        }
    }
}