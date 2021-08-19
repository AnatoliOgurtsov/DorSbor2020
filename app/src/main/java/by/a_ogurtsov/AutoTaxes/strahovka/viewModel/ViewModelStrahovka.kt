package by.a_ogurtsov.AutoTaxes.strahovka.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.a_ogurtsov.AutoTaxes.*
import by.a_ogurtsov.AutoTaxes.strahovka.City
import by.a_ogurtsov.AutoTaxes.strahovka.repository.RepositoryStrahovka

class ViewModelStrahovka(applicationContext: Context?) : ViewModel() {

    private val repositoryStrahovka = RepositoryStrahovka()

    private val context by lazy { applicationContext }
    /*private val _locationList = MutableLiveData<List<City>>()
    val locationList: LiveData<List<City>> get() = _locationList*/

    private var _totalSumValue = MutableLiveData<String>()
    val totalSumValue: LiveData<String> get() = _totalSumValue

    private var _locationK: Float = 1.5F
    val locationK: Float get() = _locationK

    private var _locationName: String = "МИНСК И МИНСКИЙ РАЙОН"
    val locationName: String get() = _locationName

    private var _autoKind: String =
        "ЛЕГКОВЫЕ АВТОМОБИЛИ ВАЗ, ЗАЗ, Москвич, АЗЛК, ИЖ, ГАЗ, ЛуАЗ, УАЗ"
    val autoKind: String get() = _autoKind

    private var _autoKindLegkDetails: String =
        "с рабочим объемом двигателя ДО 1200 куб. см. включительно"
    val autoKindLegkDetails: String get() = _autoKindLegkDetails

    private var _autoKindElectroGibridDetails: String =
        "ЭЛЕКТРОМОБИЛИ"
    val autoKindElectroGibridDetails: String get() = _autoKindElectroGibridDetails




    private lateinit var sprefStarhovka: SharedPreferences

    fun initSpref() {
        sprefStarhovka =
            context!!.getSharedPreferences(SPREF_STRAHOVKA_NAME, Context.MODE_PRIVATE)
        if (sprefStarhovka.contains(SPREF_STRAHOVKA_LOCATION_K)) {
            this._locationK = sprefStarhovka.getFloat(SPREF_STRAHOVKA_LOCATION_K, 0.0F)
            this._locationName =
                sprefStarhovka.getString(SPREF_STRAHOVKA_LOCATION_NAME, "").toString()
            this._autoKind = sprefStarhovka.getString(SPREF_STRAHOVKA_AUTO_KIND, "").toString()
            this._autoKindLegkDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_LEGK_DETAILS, "").toString()
            this._autoKindElectroGibridDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_ELECTRO_GIBRID_DETAILS, "").toString()

            setSumValue()
        } else {
            val editor: SharedPreferences.Editor = sprefStarhovka.edit()
            editor.putFloat(SPREF_STRAHOVKA_LOCATION_K, this._locationK)
            editor.putString(SPREF_STRAHOVKA_LOCATION_NAME, this._locationName)
            editor.putString(SPREF_STRAHOVKA_AUTO_KIND, this._autoKind)
            editor.putString(SPREF_STRAHOVKA_AUTO_KIND_LEGK_DETAILS, this._autoKindLegkDetails)
            editor.putString(SPREF_STRAHOVKA_AUTO_KIND_ELECTRO_GIBRID_DETAILS, this._autoKindElectroGibridDetails)
            editor.apply()
            setSumValue()
        }
    }

    fun putLocationToSharedPref(name: String, k: Float) {
        this._locationK = k
        this._locationName = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putFloat(SPREF_STRAHOVKA_LOCATION_K, k)
        editor.putString(SPREF_STRAHOVKA_LOCATION_NAME, name)
        editor.apply()
        setSumValue()
    }

    fun putAutoKindToSharedPref(name: String) {
        this._autoKind = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND, name).apply()
        setSumValue()
    }

    fun putAutoKindLegkDetailsToSharedPref(name: String) {
        this._autoKindLegkDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_LEGK_DETAILS, name).apply()
        setSumValue()
    }

    fun putAutoKindElectroGibridDetailsToSharedPref(name: String) {
        this._autoKindElectroGibridDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_ELECTRO_GIBRID_DETAILS, name).apply()
        setSumValue()
    }

    fun setSumValue() {
        _totalSumValue.value =
            repositoryStrahovka.totalSumValue(
                k1 = this._locationK,
                autoKind = this._autoKind,
                autoKindLegkDetails = this.autoKindLegkDetails,
                autoKindElectroGibridDetails = this.autoKindElectroGibridDetails

            )
    }


}