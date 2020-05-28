package com.app.whatsapptools.ui.countrycode.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.app.whatsapptools.R
import com.app.whatsapptools.callback.RecyclerViewItemClickCallback
import com.app.whatsapptools.ui.dashboard.viewmodel.DashboardViewModel
import com.app.whatsapptools.ui.countrycode.adapter.CountryCodesAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_dialog_country_code.*

class CountryCodesDialogFragment : BottomSheetDialogFragment(), RecyclerViewItemClickCallback {
    val dashboardViewModel: DashboardViewModel by activityViewModels()
    private lateinit var countryCodesAdapter: CountryCodesAdapter
    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_country_code, container, false)

        dialog?.setOnShowListener {
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                bottomSheetBehavior = BottomSheetBehavior.from(it)
                bottomSheetBehavior?.isFitToContents = false
                bottomSheetBehavior?.isHideable = true
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        countryCodesAdapter =
            CountryCodesAdapter(this)
        recycler_view_country_codes.adapter = countryCodesAdapter

        edit_text_country_code.doAfterTextChanged {
            countryCodesAdapter.filter.filter(it)
        }

        dashboardViewModel.getCountryCodeList(resources = resources)

        dashboardViewModel.countryCodes.observe(this, Observer {
            countryCodesAdapter.newDataInserted(it)
        })
    }

    override fun onItemClick(view: View?, position: Int) {
        when(view?.id) {
            R.id.container_country_code -> {
                val currentItem = countryCodesAdapter.currentList[position]
                dashboardViewModel.updateCountryCode(currentItem)
                dashboardViewModel.selectedCountryCodeLiveData.value = currentItem
                dismiss()
            }
        }
    }
}