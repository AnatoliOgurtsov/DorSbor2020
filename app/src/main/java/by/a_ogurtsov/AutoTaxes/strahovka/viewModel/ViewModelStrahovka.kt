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

    private var _autoKindLegkRusDetails: String =
        "с рабочим объемом двигателя ДО 1200 куб. см. включительно"
    val autoKindLegkRusDetails: String get() = _autoKindLegkRusDetails


    private var _autoKindLegkDetails: String =
        "с рабочим объемом двигателя ДО 1200 куб. см. включительно"
    val autoKindLegkDetails: String get() = _autoKindLegkDetails


    private var _autoKindElectroGibridDetails: String =
        "ЭЛЕКТРОМОБИЛИ"
    val autoKindElectroGibridDetails: String get() = _autoKindElectroGibridDetails

    private var _autoKindLegkPricepDetails: String =
        "ГРУЗОВЫЕ И СКЛАДНЫЕ ЖИЛЫЕ"
    val autoKindLegkPricepDetails: String get() = _autoKindLegkPricepDetails

    private var _autoKindGruzDetails: String =
        "грузоподъемностью ДО 1 т. включительно"
    val autoKindGruzDetails: String get() = _autoKindGruzDetails

    private var _autoKindTractorDetails: String =
        "с мощностью двигателя ДО 50 л.с. включительно"
    val autoKindTractorDetails: String get() = _autoKindTractorDetails

    private var _autoKindGruzPricepDetails: String =
        "грузоподъемностью ДО 10 т. включительно"
    val autoKindGruzPricepDetails: String get() = _autoKindGruzPricepDetails

    private var _autoKindMotoDetails: String =
        "с рабочим объемом двигателя ДО 150 куб. см. включительно"
    val autoKindMotoDetails: String get() = _autoKindMotoDetails

    private var _autoKindBusDetails: String =
        "с числом мест для сидения ДО 20 включительно"
    val autoKindBusDetails: String get() = _autoKindBusDetails


    private lateinit var sprefStarhovka: SharedPreferences

    fun initSpref() {
        sprefStarhovka =
            context!!.getSharedPreferences(SPREF_STRAHOVKA_NAME, Context.MODE_PRIVATE)
        if (sprefStarhovka.contains(SPREF_STRAHOVKA_LOCATION_K)) {
            this._locationK = sprefStarhovka.getFloat(SPREF_STRAHOVKA_LOCATION_K, 0.0F)
            this._locationName =
                sprefStarhovka.getString(SPREF_STRAHOVKA_LOCATION_NAME, "").toString()
            this._autoKind = sprefStarhovka.getString(SPREF_STRAHOVKA_AUTO_KIND, "").toString()
            this._autoKindLegkRusDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_LEGK_RUS_DETAILS, ""
            ).toString()
            this._autoKindLegkDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_LEGK_DETAILS, ""
            ).toString()
            this._autoKindElectroGibridDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_ELECTRO_GIBRID_DETAILS, ""
            ).toString()
            this._autoKindLegkPricepDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_LEGK_PRICEP_DETAILS, ""
            ).toString()
            this._autoKindGruzDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_GRUZ_DETAILS, ""
            ).toString()
            this._autoKindTractorDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_TRACTOR_DETAILS, ""
            ).toString()
            this._autoKindGruzPricepDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_GRUZ_PRICEP_DETAILS, ""
            ).toString()
            this._autoKindMotoDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_MOTO_DETAILS, ""
            ).toString()
            this._autoKindBusDetails = sprefStarhovka.getString(
                SPREF_STRAHOVKA_AUTO_KIND_BUS_DETAILS, ""
            ).toString()

            setSumValue()
        } else {
            val editor: SharedPreferences.Editor = sprefStarhovka.edit()
            editor.putFloat(SPREF_STRAHOVKA_LOCATION_K, this._locationK)
            editor.putString(SPREF_STRAHOVKA_LOCATION_NAME, this._locationName)
            editor.putString(SPREF_STRAHOVKA_AUTO_KIND, this._autoKind)
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_LEGK_RUS_DETAILS,
                this._autoKindLegkRusDetails
            )
            editor.putString(SPREF_STRAHOVKA_AUTO_KIND_LEGK_DETAILS, this._autoKindLegkDetails)
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_ELECTRO_GIBRID_DETAILS,
                this._autoKindElectroGibridDetails
            )
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_LEGK_PRICEP_DETAILS,
                this._autoKindLegkPricepDetails
            )
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_GRUZ_DETAILS,
                this._autoKindGruzDetails
            )
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_TRACTOR_DETAILS,
                this._autoKindTractorDetails
            )
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_GRUZ_PRICEP_DETAILS,
                this._autoKindGruzPricepDetails
            )
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_MOTO_DETAILS,
                this._autoKindMotoDetails
            )
            editor.putString(
                SPREF_STRAHOVKA_AUTO_KIND_BUS_DETAILS,
                this._autoKindBusDetails
            )
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

    fun putAutoKindLegkRusDetailsToSharedPref(name: String) {
        this._autoKindLegkRusDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_LEGK_RUS_DETAILS, name).apply()
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

    fun putAutoKindLegkPricepDetailsToSharedPref(name: String) {
        this._autoKindLegkPricepDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_LEGK_PRICEP_DETAILS, name).apply()
        setSumValue()
    }

    fun putAutoKindGruzDetailsToSharedPref(name: String) {
        this._autoKindGruzDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_GRUZ_DETAILS, name).apply()
        setSumValue()
    }

    fun putAutoKindTractorDetailsToSharedPref(name: String) {
        this._autoKindTractorDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_TRACTOR_DETAILS, name).apply()
        setSumValue()
    }

    fun putAutoKindGruzPricepDetailsToSharedPref(name: String) {
        this._autoKindGruzPricepDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_GRUZ_PRICEP_DETAILS, name).apply()
        setSumValue()
    }

    fun putAutoKindMotoDetailsToSharedPref(name: String) {
        this._autoKindMotoDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_MOTO_DETAILS, name).apply()
        setSumValue()
    }

    fun putAutoKindBusDetailsToSharedPref(name: String) {
        this._autoKindBusDetails = name
        val editor: SharedPreferences.Editor = sprefStarhovka.edit()
        editor.putString(SPREF_STRAHOVKA_AUTO_KIND_BUS_DETAILS, name).apply()
        setSumValue()
    }


    fun setSumValue() {
        _totalSumValue.value =
            repositoryStrahovka.totalSumValue(
                k1 = this._locationK,
                autoKind = this._autoKind,
                autoKindLegkRusDetails = this.autoKindLegkRusDetails,
                autoKindLegkDetails = this.autoKindLegkDetails,
                autoKindElectroGibridDetails = this.autoKindElectroGibridDetails,
                autoKindLegkPricepDetails = this.autoKindLegkPricepDetails,
                autoKindGruzDetails = this.autoKindGruzDetails,
                autoKindTractorDetails = this.autoKindTractorDetails,
                autoKindGruzPricepDetails = this.autoKindGruzPricepDetails,
                autoKindMotoDetails = this.autoKindMotoDetails,
                autoKindBusDetails = this.autoKindBusDetails
            )
    }


}