package by.a_ogurtsov.AutoTaxes.utilSbor.viewModel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import by.a_ogurtsov.AutoTaxes.utilSbor.repository.RepositoryUtilSbor

class ViewModelUtilSbor(application: Application) : AndroidViewModel(application) {

    private val repository: RepositoryUtilSbor = RepositoryUtilSbor()

    fun <T> putSprefs(sprefs: SharedPreferences, key: String, value: T) {
        val editor: SharedPreferences.Editor = sprefs.edit()
        when (value) {
            is Int -> editor.putInt(key, value)
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
        }
        editor.apply()
    }

    fun calculateSumsValue(
        kindAuto: String,
        age: Int,
        legkCarGibridCapacity: String,
        usLegkCarWeight: String,
        usGruzCarWeight: String,
        usBusEngine: String,
        usDumptruckWeight: String,
        usPricepWeight: String
    ): String {
        return repository.totalAmount(
            kindAuto,
            age,
            legkCarGibridCapacity,
            usLegkCarWeight,
            usGruzCarWeight,
            usBusEngine,
            usDumptruckWeight,
            usPricepWeight
        )
   }

}