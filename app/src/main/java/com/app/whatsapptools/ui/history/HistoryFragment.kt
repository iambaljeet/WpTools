package com.app.whatsapptools.ui.history

import android.os.Bundle
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.whatsapptools.R
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareTransitions()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTransitions()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private fun prepareTransitions() {
        postponeEnterTransition()
    }

    private fun startTransitions() {
        enterTransition = MaterialContainerTransform().apply {
            startView = requireActivity().findViewById(R.id.fab_history)
            endView = container_layout
            duration = 300
            interpolator = AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_slow_in)
        }
        returnTransition = Slide().apply {
            duration = 225
            interpolator = AnimationUtils.loadInterpolator(context, android.R.interpolator.accelerate_cubic)
        }
        startPostponedEnterTransition()
    }
}