package by.a_ogurtsov.AutoTaxes.viewModels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import by.a_ogurtsov.AutoTaxes.repositories.RepositoryUtilSbor

class ViewModelUtilSbor(application: Application) : AndroidViewModel(application) {

    val repository: RepositoryUtilSbor = RepositoryUtilSbor()

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
        us_bus_engine: String,
        us_dumpTruck_weight: String,
        us_pricep_weight: String
    ): String {
        return repository.totalAmount(
            kind_auto,
            age,
            legk_car_gibrid_switch,
            legk_car_gibrid_capacity,
            us_gruz_car_weight,
            us_bus_engine,
            us_dumpTruck_weight,
            us_pricep_weight
        )

        /*"$kind_auto + $age + $legk_car_gibrid_switch + $legk_car_gibrid_capacity + " +
                "$us_gruz_car_weight + $us_bus_engine + $us_dumpTruck_weight + $us_pricep_weight"*/
    }

}