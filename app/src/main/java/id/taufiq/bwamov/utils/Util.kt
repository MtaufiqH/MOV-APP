package id.taufiq.bwamov.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

/**
 * Created By Taufiq on 10/17/2020.
 *
 */


fun delayFunction(function: () -> Unit, delay: Long) {
    @Suppress("DEPRECATION")
    Handler().postDelayed(function, delay)
}


fun <T> Context.startActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun Context.showToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()