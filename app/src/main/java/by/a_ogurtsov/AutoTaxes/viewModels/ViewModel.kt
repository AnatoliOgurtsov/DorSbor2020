package by.a_ogurtsov.AutoTaxes.viewModels

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.a_ogurtsov.AutoTaxes.MainActivity


class MyViewModel(application: Application) : AndroidViewModel(application) {

    val currentColor: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val choice_from_fragmentStart: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun <T> putSprefs (sprefs: SharedPreferences, key: String, value: T) {
        val editor: SharedPreferences.Editor = sprefs.edit()
        when (value){
            is Int -> editor.putInt(key, value)
            is String -> editor.putString(key, value)
        }
        editor.apply()
        Log.d("myLogs", "$key $value")
    }

    fun calculateSumsValue(
        period: String,
        fizYur: String,
        age: Int,
        kindAuto: String,
        weightAuto: String,
        veteran: Int
    ): String {
        when (fizYur) {
            "fiz" -> {


            } //end fiz
            "yur" -> {

            } //end yur


        } // end fizyur


        return "$period + $fizYur + $age + $kindAuto + $weightAuto + $veteran"
    }
}