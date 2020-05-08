package com.app.whatsapptools.ui.dashboard.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.app.whatsapptools.R
import com.app.whatsapptools.di.component.DaggerViewModelComponent
import com.app.whatsapptools.ui.dashboard.viewpager.DashboardViewModel
import com.app.whatsapptools.utility.applyTopInsets
import kotlinx.android.synthetic.main.dashboard_activity.*

class DashboardActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener,
    View.OnClickListener, NavController.OnDestinationChangedListener {
    val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        DaggerViewModelComponent.builder().build().inject(this)

//        DaggerViewModelComponent
//            .builder()
//            .viewModelModule(ViewModelModule())
//            .build()
//            .inject(this)

        bottom_bar_dashboard.setOnMenuItemClickListener(this)
        fab_history.setOnClickListener(this)

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

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab_history -> {
                fab_history.hide()
                findNavController(R.id.fragment_nav)
                    .navigate(R.id.historyFragment)
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id) {
            R.id.historyFragment -> {
                fab_history.hide()
            }
            else -> {
                fab_history.show()
            }
        }
    }
}