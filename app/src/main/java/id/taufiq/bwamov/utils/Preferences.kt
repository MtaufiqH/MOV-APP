package id.taufiq.bwamov.utils

import android.content.Context

/**
 * Created By Taufiq on 10/17/2020.
 *
 */
class Preferences(context: Context) {

    companion object {
        const val PREFS = "preferences"
    }

    var sharedPreferences = context.getSharedPreferences(PREFS, 0)

    fun setValue(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(key, value)
            apply()
        }

    }

    fun getValue(key: String): String? {
        return sharedPreferences.getString(key, "")
    }


}