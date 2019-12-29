package by.a_ogurtsov.AutoTaxes

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class SharedPreferenceLiveData<T>(
    val pref: SharedPreferences,
    val key: String,
    val defValue: T
) : LiveData<T>() {


    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->

            if (key == this.key) {
              value = getValueFromPreferences(key, defValue)
            }
        }

    abstract fun getValueFromPreferences(key: String, defValue: T): T

    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences(key, defValue)
        pref.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        super.onInactive()
        pref.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
    }
}

class SharedPreferenceStringLiveData(sharedPref: SharedPreferences, key: String, defValue: String) :
    SharedPreferenceLiveData<String>(sharedPref, key, defValue) {
override fun getValueFromPreferences(key: String, defValue: String): String = pref.getString(key, defValue)
}

class SharedPreferenceIntLiveData(sharedPref: SharedPreferences, key: String, defValue: Int) :
    SharedPreferenceLiveData<Int>(sharedPref, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Int): Int = pref.getInt(key, defValue)
}

class SharedPreferenceBooleanLiveData(sharedPref: SharedPreferences, key: String, defValue: Boolean) :
    SharedPreferenceLiveData<Boolean>(sharedPref, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Boolean): Boolean = pref.getBoolean(key, defValue)
}

fun SharedPreferences.stringLiveData(key: String, defValue: String): SharedPreferenceLiveData<String>{
    return SharedPreferenceStringLiveData(this, key, defValue)
}

fun SharedPreferences.intLiveData(key: String, defValue: Int): SharedPreferenceLiveData<Int>{
    return SharedPreferenceIntLiveData(this, key, defValue)
}

fun SharedPreferences.booleanLiveData(key: String, defValue: Boolean): SharedPreferenceLiveData<Boolean>{
    return SharedPreferenceBooleanLiveData(this, key, defValue)
}
