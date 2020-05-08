package com.app.whatsapptools.utility

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View

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
        setPadding(0, insets.stableInsetTop + (additionalTopPadding ?: 0), 0, 0)
        insets.consumeSystemWindowInsets()
    }
}

fun View.getCenter(): Pair<Float, Float> {
    val cx = this.x + this.width / 2
    val cy = this.y + this.height / 2
    return Pair(cx, cy)
}