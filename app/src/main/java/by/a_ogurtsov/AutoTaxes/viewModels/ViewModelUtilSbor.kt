package by.a_ogurtsov.AutoTaxes.viewModels

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class ViewModelUtilSbor(application: Application) : AndroidViewModel(application) {

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
        kind_auto: String,
        age: Int,
        legk_car_gibrid_switch: Boolean,
        legk_car_gibrid_capacity: String,
        us_gruz_car_weight: String,
        us_bus_engine: String
    ): String {
        return "$kind_auto + $age + $legk_car_gibrid_switch + $legk_car_gibrid_capacity + $us_gruz_car_weight + $us_bus_engine"
    }


}