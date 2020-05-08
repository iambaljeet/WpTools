package com.app.whatsapptools.di.component

import com.app.whatsapptools.di.module.ViewModelModule
import com.app.whatsapptools.di.scope.ActivityScope
import com.app.whatsapptools.ui.dashboard.activity.DashboardActivity
import com.app.whatsapptools.ui.nonumber.CountryCodesDialogFragment
import com.app.whatsapptools.ui.nonumber.NoNumberFragment
import dagger.Component

@Component(modules = [ViewModelModule::class])
@ActivityScope
interface ViewModelComponent {
    fun inject(countryCodesDialogFragment: CountryCodesDialogFragment)
    fun inject(noNumberFragment: NoNumberFragment)
    fun inject(dashboardActivity: DashboardActivity)
}