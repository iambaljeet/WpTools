package com.app.whatsapptools.utility

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.whatsapptools.callback.DialogCallback

inline fun <reified T: Any> Context.launch(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T: Any> newIntent(context: Context) = Intent(context, T::class.java)

fun Context.openWhatsApp(fullPhoneNumber: String, message: String) {
    val uri: Uri =
        Uri.parse("https://api.whatsapp.com/send?phone=$fullPhoneNumber&text=$message")
    val sendIntent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(sendIntent)
}

fun View.applyTopInsets(additionalTopPadding: Int? = 0) {
    setOnApplyWindowInsetsListener { v, insets ->
        setPadding(0, insets.systemWindowInsetTop + (additionalTopPadding ?: 0), 0, 0)
        insets.consumeSystemWindowInsets()
    }
}

fun Context.showOneButtonAlertDialog(title: String, subTitle: String? = null, positiveButtonText: String,
                                     dialogCallback: DialogCallback? = null
) {
    val dialog = DialogUtility.buildOneButtonAlertDialog(
        this,
        title,
        subTitle,
        positiveButtonText,
        dialogCallback
    )
    dialog.show()
}

fun Context.buildTwoButtonsAlertDialog(title: String, subTitle: String? = null, positiveButtonText: String, negativeButtonText: String,
                                       dialogCallback: DialogCallback? = null
) {
    val dialog = DialogUtility.buildTwoButtonsAlertDialog(
        this,
        title,
        subTitle,
        positiveButtonText,
        negativeButtonText,
        dialogCallback
    )
    dialog.show()
}

fun String?.trueIfBlank(): Boolean {
    return this == null || this.isNullOrBlank()
}

fun Context.logError(message: String?) {
    Log.e(this::class.java.simpleName, message ?: "")
}

fun Context.logDebug(message: String?) {
    Log.d(this::class.java.simpleName, message ?: "")
}

fun Context.logVerbose(message: String?) {
    Log.v(this::class.java.simpleName, message ?: "")
}

fun Context.logInfo(message: String?) {
    Log.i(this::class.java.simpleName, message ?: "")
}

fun Context.showSmallToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}