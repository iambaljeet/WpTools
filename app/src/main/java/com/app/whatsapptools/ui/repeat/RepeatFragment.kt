package com.app.whatsapptools.ui.repeat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.whatsapptools.R

class RepeatFragment : Fragment() {

    companion object {
        fun newInstance() = RepeatFragment()
    }

    private lateinit var viewModel: RepeatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repeat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepeatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}