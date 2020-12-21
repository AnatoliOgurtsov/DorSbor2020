package by.a_ogurtsov.AutoTaxes.viewModels

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import by.a_ogurtsov.AutoTaxes.repositories.RepositoryDorSbor
import by.a_ogurtsov.AutoTaxes.repositories.RepositoryDorSbor21


class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RepositoryDorSbor = RepositoryDorSbor()
    private val repository21: RepositoryDorSbor21 = RepositoryDorSbor21()

    val currentColor: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val isShowCurrentRate: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val choiceFromFragmentStart: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val euroRate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val dollarRate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    fun <T> putSprefs(sprefs: SharedPreferences, key: String, value: T) {
        val editor: SharedPreferences.Editor = sprefs.edit()
        when (value) {
            is Int -> editor.putInt(key, value)
            is String -> editor.putString(key, value)
        }
        editor.apply()
        Log.d("myLogs", "$key $value")

    }
//2020====================================
    fun calculateSumsValue(
        period: String,
        fizYur: String,
        age: Int,
        kindAuto: String,
        weightAuto: String,
        veteran: Int
    ): MutableList<String> =
        repository.totalAmount(period, fizYur, age, kindAuto, weightAuto, veteran)
//=========================================
    fun calculateSumsValue21(
        fizYur: String,
        kindAuto: String,
        weightAuto: String,
        veteran: Int
    ): String =
        repository21.totalAmount21(fizYur, kindAuto, weightAuto, veteran)

}