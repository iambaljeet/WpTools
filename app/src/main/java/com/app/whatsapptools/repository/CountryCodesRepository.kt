package com.app.whatsapptools.repository

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.app.whatsapptools.R
import com.app.whatsapptools.model.CountryCodesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CountryCodesRepository {

    private val _countryCodes: MutableLiveData<List<CountryCodesModel.CountryCodesModelItem>> = MutableLiveData()
    val countryCodes: MutableLiveData<List<CountryCodesModel.CountryCodesModelItem>>
        get() = _countryCodes

    fun getCountryCodesList(resources: Resources) {
        if (_countryCodes.value == null) {
            val text = resources.openRawResource(R.raw.country_codes)
                .bufferedReader().use { it.readText() }
            val gson = Gson()
            val listType =
                object : TypeToken<List<CountryCodesModel.CountryCodesModelItem>>() {}.type

            val countryCodesList: List<CountryCodesModel.CountryCodesModelItem> =
                gson.fromJson(text, listType)
            _countryCodes.value = countryCodesList
        }
    }

    fun updateCountryCodeList(item: CountryCodesModel.CountryCodesModelItem) {
        val updatedList: ArrayList<CountryCodesModel.CountryCodesModelItem> = arrayListOf()
        _countryCodes.value?.let { updatedList.addAll(it) }

        if (updatedList.size > 0) {
            _countryCodes.value?.forEachIndexed { index, countryCodesModelItem ->
                val shouldCheck = item.code == countryCodesModelItem.code
                if (countryCodesModelItem.isSelected != shouldCheck) {
                    updatedList[index] = countryCodesModelItem.copy(isSelected = shouldCheck)
                }
            }
            _countryCodes.value = updatedList
        }
    }
}