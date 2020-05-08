package com.app.whatsapptools.ui.nonumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.app.whatsapptools.R
import com.app.whatsapptools.di.component.DaggerViewModelComponent
import com.app.whatsapptools.model.CountryCodesModel
import com.app.whatsapptools.ui.dashboard.viewpager.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_no_number.*

class NoNumberFragment : Fragment(), View.OnClickListener {
    val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_no_number, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text_view_country_code_picker.setOnClickListener(this)

        DaggerViewModelComponent.builder().build().inject(this)

//        DaggerViewModelComponent
//            .builder()
//            .viewModelModule(ViewModelModule())
//            .build()
//            .inject(this)

        dashboardViewModel.selectedCountryCodeLiveData.observe(viewLifecycleOwner, Observer {
            text_view_country_code_picker.text = "${it.dial_code} (${it.code})"
        })
    }

    fun validateFields(phoneNumber: String): Boolean {
        return if (phoneNumber.isEmpty()) {
            text_input_message?.error = "Please enter phone number"
            false
        } else {
            true
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.text_view_country_code_picker -> {
                openCountryCodeBottomSheet()
            }
        }
    }

    private fun openCountryCodeBottomSheet() {
        val countryCodesDialogFragment = CountryCodesDialogFragment()
        countryCodesDialogFragment.show(childFragmentManager, countryCodesDialogFragment.tag)
    }

    fun onCountryCodeSelected(item: CountryCodesModel.CountryCodesModelItem) {
        dashboardViewModel.selectedCountryCodeLiveData.value = item
    }
}