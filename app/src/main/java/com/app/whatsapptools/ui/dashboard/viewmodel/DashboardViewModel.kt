package com.app.whatsapptools.ui.dashboard.viewmodel

import android.content.res.Resources
import androidx.lifecycle.*
import com.app.whatsapptools.app.WpToolsApp
import com.app.whatsapptools.database.dao.MessageDao
import com.app.whatsapptools.database.entitity.MessagesTextEntity
import com.app.whatsapptools.model.CountryCodesModel
import com.app.whatsapptools.repository.CountryCodesRepository
import com.app.whatsapptools.repository.MessagesHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    var selectedCountryCodeLiveData: MutableLiveData<CountryCodesModel.CountryCodesModelItem> = MutableLiveData()
    var countryCodes: MutableLiveData<List<CountryCodesModel.CountryCodesModelItem>> = CountryCodesRepository.countryCodes

    var enteredCountryCode: MutableLiveData<String> = MutableLiveData()
    var enteredPhoneNumber: MutableLiveData<String> = MutableLiveData()
    var enteredMessage: MutableLiveData<String> = MutableLiveData()

    fun getAllSavedMessages(): LiveData<MutableList<MessagesTextEntity>> {
        return liveData(Dispatchers.IO) {
            emitSource(MessagesHistoryRepository.getPastMessages())
        }
    }

    fun getCountryCodeList(resources: Resources) {
        viewModelScope.launch {
            CountryCodesRepository.getCountryCodesList(resources)
        }
    }

    fun updateCountryCode(item: CountryCodesModel.CountryCodesModelItem) {
        CountryCodesRepository.updateCountryCodeList(item)
    }

    fun saveMessageToDb(countryCode: String, phoneNumber: String, message: String, dateTime: Long) {
        val messagesTextEntity = MessagesTextEntity(
            messageText = message, countryCode = countryCode, phoneNumber = phoneNumber, dateTime = dateTime
        )

        viewModelScope.launch(Dispatchers.IO) {
            MessagesHistoryRepository.addMessage(messagesTextEntity)
        }
    }
}