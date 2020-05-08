package com.app.whatsapptools.model

import androidx.recyclerview.widget.DiffUtil

class CountryCodesModel : ArrayList<CountryCodesModel.CountryCodesModelItem>(){
    data class CountryCodesModelItem(
        val code: String,
        val dial_code: String,
        val name: String,
        var isSelected: Boolean = false
    )
}

class DiffUtilsCountryCodesModelItem: DiffUtil.ItemCallback<CountryCodesModel.CountryCodesModelItem>() {
    override fun areItemsTheSame(oldItem: CountryCodesModel.CountryCodesModelItem,
                                 newItem: CountryCodesModel.CountryCodesModelItem): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(
        oldItem: CountryCodesModel.CountryCodesModelItem,
        newItem: CountryCodesModel.CountryCodesModelItem
    ): Boolean {
        return oldItem == newItem
    }
}