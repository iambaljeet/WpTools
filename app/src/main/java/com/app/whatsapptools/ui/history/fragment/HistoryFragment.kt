package com.app.whatsapptools.ui.history.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.whatsapptools.R
import com.app.whatsapptools.callback.RecyclerViewItemClickCallback
import com.app.whatsapptools.database.entitity.MessagesTextEntity
import com.app.whatsapptools.ui.dashboard.viewmodel.DashboardViewModel
import com.app.whatsapptools.ui.history.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.history_fragment.*
import java.util.*

class HistoryFragment : Fragment(), RecyclerViewItemClickCallback {
    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    private lateinit var historyAdapter: HistoryAdapter
    private var dataList = mutableListOf<MessagesTextEntity>()

    private var messagesListObserver = Observer<MutableList<MessagesTextEntity>> { list ->
        this.dataList = list
        setData(dataList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        historyAdapter = HistoryAdapter(this)
        recycler_view_messages.adapter = historyAdapter

        edit_text_search.doOnTextChanged { text, start, before, count ->
            filterList(text)
        }

        val allSavedMessagesLivaData = dashboardViewModel.getAllSavedMessages()
        allSavedMessagesLivaData.observe(viewLifecycleOwner, messagesListObserver)
    }

    override fun onItemClick(view: View?, position: Int) {
        when(view?.id) {
            R.id.container_main -> {
                val messageTextEntity = historyAdapter.currentList[position]

                dashboardViewModel.enteredCountryCode.value = messageTextEntity.countryCode
                dashboardViewModel.enteredPhoneNumber.value = messageTextEntity.phoneNumber
                dashboardViewModel.enteredMessage.value = messageTextEntity.messageText

                findNavController().popBackStack()
            }
        }
    }

    private fun setData(dataList: List<MessagesTextEntity>) {
        if (dataList.isNotEmpty()) {
            historyAdapter.submitList(dataList)
            recycler_view_messages?.isVisible = true
            text_view_no_data?.isVisible = false
        } else {
            text_view_no_data?.isVisible = true
            recycler_view_messages?.isVisible = false
        }
    }

    private fun filterList(text: CharSequence?) {
        val filteredList = dataList.filter { messagesTextEntity ->
            val textToSearch = text?.toString()?.toLowerCase(Locale.getDefault()) ?: String()
            val message = messagesTextEntity.messageText.toLowerCase(Locale.getDefault())
            val phoneNumber = messagesTextEntity.phoneNumber.toLowerCase(Locale.getDefault())
            message.contains(textToSearch) || phoneNumber.contains(textToSearch)
        }
        setData(filteredList)
    }
}