package com.app.whatsapptools.callback

import android.view.View

interface RecyclerViewItemClickCallback {
    fun onItemClick(view: View?, position: Int)
}