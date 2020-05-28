package com.app.whatsapptools.callback

import android.content.DialogInterface

interface DialogCallback {
    fun onPositiveButtonClicked(dialogInterface: DialogInterface)
    fun onNegativeButtonClicked(dialogInterface: DialogInterface)
}