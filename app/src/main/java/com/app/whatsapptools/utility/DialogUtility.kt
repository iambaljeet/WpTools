package com.app.whatsapptools.utility

import android.content.Context
import com.app.whatsapptools.callback.DialogCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtility {
    fun buildTwoButtonsAlertDialog(context: Context, title: String, subTitle: String? = null, positiveButtonText: String, negativeButtonText: String,
                                   dialogCallback: DialogCallback? = null): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(subTitle)
            .setPositiveButton(positiveButtonText) { dialog, which ->
                dialogCallback?.onPositiveButtonClicked(dialog)
                dialog.dismiss()
            }
            .setNegativeButton(negativeButtonText) { dialog, which ->
                dialogCallback?.onNegativeButtonClicked(dialog)
                dialog.dismiss()
            }
    }

    fun buildOneButtonAlertDialog(context: Context, title: String, subTitle: String? = null, positiveButtonText: String,
                                  dialogCallback: DialogCallback? = null): MaterialAlertDialogBuilder {
        return  MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(subTitle)
            .setPositiveButton(positiveButtonText) { dialog, which ->
                dialogCallback?.onPositiveButtonClicked(dialog)
                dialog.dismiss()
            }
    }
}