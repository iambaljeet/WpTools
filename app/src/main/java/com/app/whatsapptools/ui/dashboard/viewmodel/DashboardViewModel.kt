package com.app.whatsapptools.ui.dashboard.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.whatsapptools.model.CountryCodesModel
import com.app.whatsapptools.repository.CountryCodesRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    var selectedCountryCodeLiveData: MutableLiveData<CountryCodesModel.CountryCodesModelItem> = MutableLiveData()
    var countryCodes: MutableLiveData<List<CountryCodesModel.CountryCodesModelItem>> = CountryCodesRepository.countryCodes

    var selectedCountryCode: String? = null
    var enteredPhoneNumber: String? = null
    var enteredMessage: String? = null
    var selectedItem: CountryCodesModel.CountryCodesModelItem? = null

    fun getCountryCodeList(resources: Resources) {
        viewModelScope.launch {
            CountryCodesRepository.getCountryCodesList(resources)
        }
    }

    fun updateCountryCode(item: CountryCodesModel.CountryCodesModelItem) {
        CountryCodesRepository.updateCountryCodeList(item)
    }
}