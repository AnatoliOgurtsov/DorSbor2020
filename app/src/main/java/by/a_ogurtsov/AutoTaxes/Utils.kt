package by.a_ogurtsov.AutoTaxes

import android.content.Context
import by.a_ogurtsov.AutoTaxes.strahovka.City
import by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details.setIcon
import com.google.android.material.button.MaterialButton
import java.io.IOException


const val LOG_TAG = "myLogs"

const val FRAGMENTSTART = "FRAGMENT_START"
const val FRAGMENTDORSBOR = "FRAGMENT_DORSBOR"
const val FRAGMENTUTILSBOR = "FRAGMENT_UTILSBOR"
const val FRAGMENTSTRAHOVKA = "FRAGMENT_STRAHOVKA"
const val FRAGMENTSETTINGS = "FRAGMENT_SETTINGS"
const val CURRENTFRAGMENT = "CURRENT_FRAGMENT"
const val CURRENTFRAGMENT_PRESS_ARROW_BACK = "CURRENT_FRAGMENT_PRESS_ARROW_BACK"

const val PREFSHOWCURRENCYRATE = "pref_showCurrencyRate"

const val SPREF_STRAHOVKA_NAME = "sprefStrahovka"
const val SPREF_STRAHOVKA_LOCATION_K = "location_k"
const val SPREF_STRAHOVKA_LOCATION_NAME = "location_name"
const val SPREF_STRAHOVKA_AUTO_KIND = "auto_kind"
const val SPREF_STRAHOVKA_AUTO_KIND_LEGK_RUS_DETAILS = "SPREF_STRAHOVKA_AUTO_KIND_LEGK_RUS_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_LEGK_DETAILS = "SPREF_STRAHOVKA_AUTO_KIND_LEGK_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_ELECTRO_GIBRID_DETAILS =
    "SPREF_STRAHOVKA_AUTO_KIND_ELECTRO_GIBRID_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_LEGK_PRICEP_DETAILS =
    "SPREF_STRAHOVKA_AUTO_KIND_LEGK_PRICEP_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_GRUZ_DETAILS = "SPREF_STRAHOVKA_AUTO_KIND_GRUZ_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_TRACTOR_DETAILS = "SPREF_STRAHOVKA_AUTO_KIND_TRACTOR_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_GRUZ_PRICEP_DETAILS =
    "SPREF_STRAHOVKA_AUTO_KIND_GRUZ_PRICEP_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_MOTO_DETAILS = "SPREF_STRAHOVKA_AUTO_KIND_MOTO_DETAILS"
const val SPREF_STRAHOVKA_AUTO_KIND_BUS_DETAILS = "SPREF_STRAHOVKA_AUTO_KIND_BUS_DETAILS"


const val baseValue: Double = 27.0


fun getJsonDataFromAsset(context: Context?, fileName: String): String? {
    val jsonString: String?
    try {
        jsonString = context?.assets?.open(fileName)?.bufferedReader().use {
            it!!.readText()
        }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

var listLocationsStrahovkaUtils: List<City>? = null

fun bindButtonLocationStrahovka(name: String?, k: Float?, button: MaterialButton) {
    button.text = name
    when (k) {
        1.5F -> button.setIconResource(R.drawable.ic_location_bigcity_24)
        1.2F -> button.setIconResource(R.drawable.ic_location_city_24)
        1.0F -> button.setIconResource(R.drawable.ic_location_town_24)
        0.8F -> button.setIconResource(R.drawable.ic_location_house_24)
    }
}

fun bindButtonAutoKindStrahovka(name: String?, button: MaterialButton) {
    button.text = name
    when (name) {
        button.resources.getString(R.string.title_button_legk_car_russia),
        button.resources.getString(R.string.title_button_legk_car_not_russia) ->
            button.setIconResource(R.drawable.ic_car_black_24dp)

        button.resources.getString(R.string.title_button_electro_gibrid_strahovka) -> button.setIconResource(
            R.drawable.ic_baseline_electric_car_24
        )
        button.resources.getString(R.string.title_button_legk_car_pricep) -> button.setIconResource(
            R.drawable.ic_pricep
        )
        button.resources.getString(R.string.title_button_gruz_car_strahovka) -> button.setIconResource(
            R.drawable.ic_gruz_black_24dp
        )
        button.resources.getString(R.string.title_button_tractor) -> button.setIconResource(
            R.drawable.ic_baseline_tractor_24
        )
        button.resources.getString(R.string.title_button_gruz_car_pricep) -> button.setIconResource(
            R.drawable.ic_pricep
        )
        button.resources.getString(R.string.title_button_moto) -> button.setIconResource(
            R.drawable.ic_motorcycle_black_24dp
        )
        button.resources.getString(R.string.title_button_bus_strahovka) -> button.setIconResource(
            R.drawable.ic_directions_bus_black_24dp
        )
    }
}

fun bindButtonAutoKindDetailsStrahovka(name: String, button: MaterialButton) {
    button.text = name
    button.setIcon(name)
}



