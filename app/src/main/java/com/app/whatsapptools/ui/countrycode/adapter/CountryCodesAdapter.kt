package com.app.whatsapptools.ui.countrycode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.whatsapptools.R
import com.app.whatsapptools.callback.RecyclerViewItemClickCallback
import com.app.whatsapptools.model.CountryCodesModel
import com.app.whatsapptools.model.DiffUtilsCountryCodesModelItem
import kotlinx.android.synthetic.main.list_item_country_codes.view.*

class CountryCodesAdapter(private val recyclerViewItemClickCallback: RecyclerViewItemClickCallback):
    ListAdapter<CountryCodesModel.CountryCodesModelItem,
            CountryCodesAdapter.ViewHolderCountryCodes>(DiffUtilsCountryCodesModelItem()), Filterable {
    private val refList: ArrayList<CountryCodesModel.CountryCodesModelItem>? = arrayListOf()
    private var countryFilterList: ArrayList<CountryCodesModel.CountryCodesModelItem>? = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderCountryCodes {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_country_codes, parent, false)
        return ViewHolderCountryCodes(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ViewHolderCountryCodes, position: Int) {
        holder.bind(getItem(position), recyclerViewItemClickCallback)
    }

    fun newDataInserted(dataList: List<CountryCodesModel.CountryCodesModelItem>) {
        refList?.clear()
        refList?.addAll(dataList)
        countryFilterList?.clear()
        countryFilterList?.addAll(dataList)
        submitList(dataList)
    }

    class ViewHolderCountryCodes(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        lateinit var recyclerViewItemClickCallback: RecyclerViewItemClickCallback
        fun bind(
            item: CountryCodesModel.CountryCodesModelItem,
            recyclerViewItemClickCallback: RecyclerViewItemClickCallback
        ) {
            this.recyclerViewItemClickCallback = recyclerViewItemClickCallback
            itemView.text_view_country_name.text = "${item.dial_code}"
            itemView.text_view_country_code.text = "${item.name} (${item.code})"

            if (item.isSelected) {
                itemView.text_view_country_name.setTextColor(ContextCompat.getColor(itemView.context, R.color.ColorSelectedCountryCodeText))
                itemView.text_view_country_code.setTextColor(ContextCompat.getColor(itemView.context, R.color.ColorSelectedCountryCodeText))
            } else {
                itemView.text_view_country_name.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorTextInverse))
                itemView.text_view_country_code.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorTextInverse))
            }

            itemView.container_country_code.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            recyclerViewItemClickCallback.onItemClick(view = v, position = adapterPosition)
        }
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchQuery = constraint.toString()
                countryFilterList?.clear()
                if (searchQuery.isBlank()) {
                    refList?.let { countryFilterList?.addAll(it) }
                } else {
                    refList?.forEach {
                        if (it.name.toLowerCase().contains(searchQuery.toLowerCase())) {
                            countryFilterList?.add(it)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<CountryCodesModel.CountryCodesModelItem>?
                submitList(countryFilterList)
                notifyDataSetChanged()
            }
        }
    }

}