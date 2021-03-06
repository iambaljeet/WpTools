package com.app.whatsapptools.ui.nonumber.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.app.whatsapptools.R
import com.app.whatsapptools.ui.countrycode.fragment.CountryCodesDialogFragment
import com.app.whatsapptools.ui.dashboard.viewmodel.DashboardViewModel
import com.app.whatsapptools.utility.openWhatsApp
import com.app.whatsapptools.utility.showOneButtonAlertDialog
import kotlinx.android.synthetic.main.fragment_no_number.*

class NoNumberFragment : Fragment(), View.OnClickListener {
    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_no_number, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text_view_country_code_picker.setOnClickListener(this)

        dashboardViewModel.selectedCountryCodeLiveData.observe(viewLifecycleOwner, Observer {
            text_view_country_code_picker.text = "${it.dial_code} (${it.code})"
        })

        dashboardViewModel.enteredCountryCode.observe(viewLifecycleOwner, Observer { countryCode ->
            text_view_country_code_picker.text = countryCode })
        dashboardViewModel.enteredPhoneNumber.observe(viewLifecycleOwner, Observer { phoneNumber ->
            edit_text_number.setText(phoneNumber) })
        dashboardViewModel.enteredMessage.observe(viewLifecycleOwner, Observer { message ->
            edit_text_message.setText(message) })

        button_send_message.setOnClickListener(this)
    }

    private fun validateFields(): Boolean {
        val countryCode = text_view_country_code_picker.text.toString().trim()
        val phoneNumber = edit_text_number.text.toString().trim()
        val message = edit_text_message.text.toString().trim()
        return when {
            countryCode.isEmpty() -> {
                showErrorDialog(getString(R.string.country_code_error_message))
                false
            }
            phoneNumber.isEmpty() -> {
                showErrorDialog(getString(R.string.phone_number_error_message))
                false
            }
            message.isEmpty() -> {
                showErrorDialog(getString(R.string.message_error_message))
                false
            }
            else -> {
                true
            }
        }
    }

    private fun showErrorDialog(errorMessage: String) {
        context?.showOneButtonAlertDialog(title = getString(R.string.error), subTitle = errorMessage, positiveButtonText = getString(R.string.ok))
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.text_view_country_code_picker -> {
                openCountryCodeBottomSheet()
            }
            R.id.button_send_message -> {
                if (validateFields()) {
                    openWhatsApp()
                }
            }
        }
    }

    private fun openWhatsApp() {
        val countryCode = text_view_country_code_picker.text.toString().trim()
        val phoneNumber = edit_text_number.text.toString().trim()
        val message = edit_text_message.text.toString().trim()
        val dateTime = System.currentTimeMillis()
        dashboardViewModel.saveMessageToDb(countryCode, phoneNumber, message, dateTime)
        context?.openWhatsApp(fullPhoneNumber = "$countryCode$phoneNumber", message = message)
    }

    private fun openCountryCodeBottomSheet() {
        val countryCodesDialogFragment =
            CountryCodesDialogFragment()
        countryCodesDialogFragment.show(childFragmentManager, countryCodesDialogFragment.tag)
    }
}