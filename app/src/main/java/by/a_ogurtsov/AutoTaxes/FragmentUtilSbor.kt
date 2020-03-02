package by.a_ogurtsov.AutoTaxes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.a_ogurtsov.AutoTaxes.viewModels.ViewModelUtilSbor
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.switchmaterial.SwitchMaterial

class FragmentUtilSbor : Fragment() {

    val LOG_TAG = "myLogs"

    private val LAYOUT: Int = R.layout.fragment_util_sbor


    private val color: String = "привет"
    private val widthScreenDPI: String = ""

    private var us_age: Int = 1            // 1 - до трех лет, 2 - старше трех лет
    private var us_kindAuto: String = "legk_car"
    private var us_legk_car_gibrid_switch = false
    private var us_legk_car_gibrid_capacity =
        "less_1000"   // if toogle gibrid car = false, than null.
    private var us_gruz_car_weight = "less_2_5t"
    private var us_bus_engine = "less_2500"
    private var usBusEngineTemp = ""
    private var usDumpTruckWeight = "50_80t"
    private var usPricepWeight = "more_10t_pricep"


    private lateinit var us_textViewSumsValue: TextView
    private lateinit var us_buttonToggleGroupVozrast: MaterialButtonToggleGroup
    private lateinit var us_buttonBefore3Years: MaterialButton
    private lateinit var us_button3YearsAndOldest: MaterialButton
    private lateinit var us_buttonKindOfAuto: MaterialButton
    private lateinit var us_switchButtonLegkGibrid: SwitchMaterial
    private lateinit var us_container: FrameLayout
    private lateinit var us_RadioGroupLegkGibridMotor: RadioGroup
    private lateinit var radioGroup_us_gibrid_motor_item: RadioButton
    private lateinit var us_buttonGruzWeight: MaterialButton
    private lateinit var us_layoutBus: LinearLayout
    private lateinit var us_layoutBusButton: MaterialButton
    private lateinit var us_layoutBusSwitchButton: SwitchMaterial
    private lateinit var us_buttonDumpTruckWeight: MaterialButton
    private lateinit var us_buttonPricepWeight: MaterialButton


    private lateinit var model: ViewModelUtilSbor
    private lateinit var sprefUtilSbor: SharedPreferences

    val SPREF_UTILSBOR_NAME = "sprefUtilsbor"
    val SPREF_UTILSBOR_KINDAUTO = "usKindAuto"
    val SPREF_UTILSBOR_AGE = "usAge"
    val SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH = "us_legk_car_gibrid_switch"
    val SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY = "us_legk_car_gibrid_capacity"
    val SPREF_UTILSBOR_GRUZ_CAR_WEIGHT = "weight_of_gruz_auto_us"
    val SPREF_UTILSBOR_BUS_ENGINE = "us_engineOfBus"
    val SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT = "us_dumpTruckWeight"
    val SPREF_UTILSBOR_PRICEP_WEIGHT = "us_pricepWeight"


    val REQUEST_CODE_KIND_OF_AUTO = 1
    val REQUEST_CODE_GRUZ_AUTO_WEIGHT = 2
    val REQUEST_CODE_BUS_ENGINE = 3
    val REQUEST_CODE_DUMPTRUCK_WEIGHT = 4
    val REQUEST_CODE_PRICEP_WEIGHT = 5


    fun newInstance(param1: String, param2: Int): FragmentUtilSbor {
        return FragmentUtilSbor().apply {
            arguments = Bundle().apply {
                putString(color, param1)
                putInt(widthScreenDPI, param2)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(LAYOUT, container, false)

        model = ViewModelProviders.of(this).get(ViewModelUtilSbor::class.java)

        us_textViewSumsValue = view.findViewById(R.id.us_textView_Sums_Value)

        initSpref()
        us_container = view.findViewById(R.id.container_us)
        us_buttonKindOfAuto = view.findViewById(R.id.us_button_kind_of_auto)
        us_buttonToggleGroupVozrast = view.findViewById(R.id.us_buttonToggleGroup_vozrast)
        us_buttonBefore3Years = view.findViewById(R.id.us_button_before_3_years)
        us_button3YearsAndOldest = view.findViewById(R.id.us_button_3_years_and_oldest)
        us_switchButtonLegkGibrid = view.findViewById(R.id.switch_button_gibrid)

        this.us_RadioGroupLegkGibridMotor = // radioGroupForLegkAutoGibrid
            layoutInflater.inflate(R.layout.us_gibrid_motor, null) as RadioGroup

        this.us_buttonGruzWeight =
            layoutInflater.inflate(R.layout.us_button_gruz_weight, null) as MaterialButton

        this.us_layoutBus = layoutInflater.inflate(R.layout.us_layout_bus, null) as LinearLayout
        this.us_layoutBusButton = us_layoutBus.findViewById(R.id.us_button_bus_engine)
        this.us_layoutBusSwitchButton = us_layoutBus.findViewById(R.id.us_switch_button_bus)

        this.us_buttonDumpTruckWeight =
            layoutInflater.inflate(R.layout.us_button_dumptruck_weight, null) as MaterialButton
        this.us_buttonPricepWeight = layoutInflater.inflate(R.layout.us_button_pricep_weight, null) as MaterialButton


        setbuttonToggleGroup(
            view,
            R.id.us_buttonToggleGroup_vozrast,
            us_buttonBefore3Years.id,
            us_button3YearsAndOldest.id
        )

        setButtonWidth(us_buttonKindOfAuto)


        //========начальные состояния buttons=========================
        //==========================================================================================
        setStartSettingbuttons()

        //==========================================================================================


        val onButtonClickListener: View.OnClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.us_button_kind_of_auto -> {
                    val intent = Intent(activity, ActivityKindOfAutoUS::class.java)
                    startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO)
                }

                R.id.us_button_before_3_years -> {
                    us_buttonBefore3Years.isChecked = true
                    model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_AGE, 1)

                }
                R.id.us_button_3_years_and_oldest -> {
                    us_button3YearsAndOldest.isChecked = true
                    model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_AGE, 2)
                }
                R.id.us_button_gruz_weight -> {
                    val intent = Intent(activity, ActivityWeightGruzAutoUs::class.java)
                    startActivityForResult(intent, REQUEST_CODE_GRUZ_AUTO_WEIGHT)
                }
                R.id.us_button_bus_engine -> {
                    val intent = Intent(activity, ActivityEngineBusUs::class.java)
                    startActivityForResult(intent, REQUEST_CODE_BUS_ENGINE)
                }
                R.id.us_button_dumpTrack_weight -> {
                    val intent = Intent(activity, ActivityWeightDumpTruckUs::class.java)
                    startActivityForResult(intent, REQUEST_CODE_DUMPTRUCK_WEIGHT)
                }

                R.id. us_button_pricep_weight -> {
                    val intent = Intent(activity, ActivityWeightPricepUs::class.java)
                    startActivityForResult(intent, REQUEST_CODE_PRICEP_WEIGHT)
                }


            }
        }
        us_buttonBefore3Years.setOnClickListener(onButtonClickListener)
        us_button3YearsAndOldest.setOnClickListener(onButtonClickListener)
        us_buttonKindOfAuto.setOnClickListener(onButtonClickListener)
        us_buttonGruzWeight.setOnClickListener(onButtonClickListener)
        us_layoutBusButton.setOnClickListener(onButtonClickListener)
        us_buttonDumpTruckWeight.setOnClickListener(onButtonClickListener)
        us_buttonPricepWeight.setOnClickListener(onButtonClickListener)




        us_switchButtonLegkGibrid.setOnCheckedChangeListener { _, isChecked ->
            // switch gibrid
            if (isChecked) {
                model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, true)
                us_container.addView(us_RadioGroupLegkGibridMotor)
                when (us_legk_car_gibrid_capacity) {
                    "less_1000" -> us_RadioGroupLegkGibridMotor.check(R.id.less_1000)
                    "in_1000_2000" -> us_RadioGroupLegkGibridMotor.check(R.id.in_1000_2000)
                    "in_2000_3000" -> us_RadioGroupLegkGibridMotor.check(R.id.in_2000_3000)
                    "in_3000_3500" -> us_RadioGroupLegkGibridMotor.check(R.id.in_3000_3500)
                    "more_3500" -> us_RadioGroupLegkGibridMotor.check(R.id.more_3500)
                }

            } else {
                model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, false)
                us_container.removeView(us_RadioGroupLegkGibridMotor)
            }
        }

        us_RadioGroupLegkGibridMotor.setOnCheckedChangeListener { radioGroup_us_gibrid_motor, checkedId ->
            // listener on legcar gibrid_motor
            when (checkedId) {
                R.id.less_1000 -> {
                    model.putSprefs(
                        sprefUtilSbor,
                        SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY,
                        "less_1000"
                    )
                }
                R.id.in_1000_2000 -> {
                    model.putSprefs(
                        sprefUtilSbor,
                        SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY,
                        "in_1000_2000"
                    )
                }
                R.id.in_2000_3000 -> {
                    model.putSprefs(
                        sprefUtilSbor,
                        SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY,
                        "in_2000_3000"
                    )
                }
                R.id.in_3000_3500 -> {
                    model.putSprefs(
                        sprefUtilSbor,
                        SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY,
                        "in_3000_3500"
                    )
                }
                R.id.more_3500 -> {
                    model.putSprefs(
                        sprefUtilSbor,
                        SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY,
                        "more_3500"
                    )
                }
            }

        }
        us_layoutBusSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            val layoutParam: ViewGroup.LayoutParams = us_layoutBusSwitchButton.layoutParams
            if (isChecked) {
                usBusEngineTemp =
                    this.us_bus_engine        // сохраняем состояние кнопки чтобы когда false передать во Preference нужное значение
                model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "electro")
                us_layoutBusButton.visibility = GONE
                us_layoutBusSwitchButton.layoutParams = layoutParam
                setSwitchButtonWidth(us_layoutBusSwitchButton)
            } else {
                us_layoutBusButton.visibility = VISIBLE
                when (usBusEngineTemp) {
                    "less_2500" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "less_2500")
                        set_us_button_busEngine(
                            R.string.title_button_bus_motor_less_2500,
                            R.drawable.ic_hexagon_outline
                        )
                    }
                    "2500_5000" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "2500_5000")
                        set_us_button_busEngine(
                            R.string.title_button_bus_motor_in_2500_5000,
                            R.drawable.ic_hexagon_slice_2
                        )
                    }
                    "5000_10000" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "5000_10000")
                        set_us_button_busEngine(
                            R.string.title_button_bus_motor_in_5000_10000,
                            R.drawable.ic_hexagon_slice_4
                        )
                    }
                    "more_10000" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "more_10000")
                        set_us_button_busEngine(
                            R.string.title_button_bus_motor_more_10000,
                            R.drawable.ic_hexagon_slice_6
                        )
                    }
                }
            }
        }

        return view
    }

    fun setbuttonToggleGroup(view: View, idButtonToogleGroup: Int, idButton1: Int, idButton2: Int) {

        val buttonToogleGroup: MaterialButtonToggleGroup = view.findViewById(idButtonToogleGroup)

        val button1: MaterialButton = view.findViewById(idButton1)
        button1.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) / 2 - 40   // устанавливаем ширину кнопки по размеру экрана

        val button2: MaterialButton = view.findViewById(idButton2)
        button2.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) / 2 - 40      // устанавливаем ширину кнопки по размеру экрана

        // устанавливаем начальное состояние кнопок возраст

        when (us_age) {
            1 -> buttonToogleGroup.check(idButton1)
            2 -> buttonToogleGroup.check(idButton2)
        }
    }

    fun setButtonWidth(button: MaterialButton) {
        button.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) - 80   // устанавливаем ширину кнопки без группы
    }

    fun setSwitchButtonWidth(switchButton: SwitchMaterial) {
        switchButton.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) - 80   // устанавливаем ширину switchButton без группы
    }

    fun initSpref() {
        sprefUtilSbor = context!!.getSharedPreferences(SPREF_UTILSBOR_NAME, Context.MODE_PRIVATE)
        if (sprefUtilSbor.contains(SPREF_UTILSBOR_AGE)) {
            this.us_kindAuto = sprefUtilSbor.getString(SPREF_UTILSBOR_KINDAUTO, "")
            this.us_age = sprefUtilSbor.getInt(SPREF_UTILSBOR_AGE, 0)
            this.us_legk_car_gibrid_switch =
                sprefUtilSbor.getBoolean(SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, false)
            this.us_legk_car_gibrid_capacity =
                sprefUtilSbor.getString(SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY, "")
            this.us_gruz_car_weight = sprefUtilSbor.getString(SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "")
            this.us_bus_engine = sprefUtilSbor.getString(SPREF_UTILSBOR_BUS_ENGINE, "")
            this.usDumpTruckWeight = sprefUtilSbor.getString(SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT, "")
            this.usPricepWeight = sprefUtilSbor.getString(SPREF_UTILSBOR_PRICEP_WEIGHT, "")
        }
        else {
            val editor: SharedPreferences.Editor = sprefUtilSbor.edit()
            editor.putString(SPREF_UTILSBOR_KINDAUTO, us_kindAuto)
            editor.putInt(SPREF_UTILSBOR_AGE, us_age)
            editor.putBoolean(SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, us_legk_car_gibrid_switch)
            editor.putString(SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY, us_legk_car_gibrid_capacity)
            editor.putString(SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, us_gruz_car_weight)
            editor.putString(SPREF_UTILSBOR_BUS_ENGINE, us_bus_engine)
            editor.putString(SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT, usDumpTruckWeight)
            editor.putString(SPREF_UTILSBOR_PRICEP_WEIGHT, usPricepWeight)
            editor.apply()
        }

        initPreferencesObserver()
    }

    fun initPreferencesObserver() {
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_KINDAUTO, "")
            .observe(this, Observer<String> { value ->
                this.us_kindAuto = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            }
            )
        sprefUtilSbor.intLiveData(SPREF_UTILSBOR_AGE, 1)
            .observe(this, Observer<Int> { value ->
                this.us_age = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            }
            )
        sprefUtilSbor.booleanLiveData(SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, false)
            .observe(this, Observer<Boolean> { value ->
                this.us_legk_car_gibrid_switch = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY, "")
            .observe(this, Observer<String> { value ->
                this.us_legk_car_gibrid_capacity = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.us_gruz_car_weight = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_BUS_ENGINE, "")
            .observe(this, Observer<String> { value ->
                this.us_bus_engine = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.usDumpTruckWeight = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })

        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_PRICEP_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.usPricepWeight = value
                us_textViewSumsValue.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight,
                        us_bus_engine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_KIND_OF_AUTO -> {              // ====================================
                    when (data!!.getStringExtra("kind_of_auto")) {
                        "legk_car" -> {

                            if (us_container.getChildAt(0) == us_buttonGruzWeight)  // delete gruzcar weight button
                                us_container.removeView(us_buttonGruzWeight)

                            if (us_container.getChildAt(0) == us_layoutBus)
                                us_container.removeView(us_layoutBus)   // delete layoutBus

                            if (us_container.getChildAt(0) == us_buttonDumpTruckWeight)
                                us_container.removeView(us_buttonDumpTruckWeight)   // delete DumpTruck

                            if (us_container.getChildAt(0) == us_buttonPricepWeight)
                                us_container.removeView(us_buttonPricepWeight)                // delete pricepButton

                            us_switchButtonLegkGibrid.visibility =
                                VISIBLE                                    // switch button for gibrid
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "legk_car")
                            set_us_button_kind_of_auto(
                                R.string.title_button_legk_car,
                                R.drawable.ic_car_black_24dp
                            )
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH,
                                false
                            )
                            us_switchButtonLegkGibrid.isChecked = false


                        }
                        "gruz_car" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "gruz_car")
                            set_us_button_kind_of_auto(
                                R.string.title_button_gruz_car_us,
                                R.drawable.ic_gruz_black_24dp
                            )
                            us_switchButtonLegkGibrid.visibility = GONE

                            if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup

                            if (us_container.getChildAt(0) == us_layoutBus)
                                us_container.removeView(us_layoutBus)                // delete layoutBus

                            if (us_container.getChildAt(0) == us_buttonDumpTruckWeight)
                                us_container.removeView(us_buttonDumpTruckWeight)   // delete DumpTruck

                            if (us_container.getChildAt(0) == us_buttonPricepWeight)
                                us_container.removeView(us_buttonPricepWeight)                // delete pricepButton

                            if (us_container.getChildAt(0) != us_buttonGruzWeight) {      //проверяем  нет ли уже кнопки gruz_weight

                                us_container.addView(this.us_buttonGruzWeight)  // add weight button for gruz_auto
                                setButtonWidth(this.us_buttonGruzWeight)
                                setStateButtonUsGruzCarWeight(us_gruz_car_weight)

                            }

                        }
                        "bus" -> {

                            if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup
                            us_switchButtonLegkGibrid.visibility = GONE

                            if (us_container.getChildAt(0) == us_buttonGruzWeight)
                                us_container.removeView(us_buttonGruzWeight)         // delete gruzcar weight button

                            if (us_container.getChildAt(0) == us_buttonDumpTruckWeight)
                                us_container.removeView(us_buttonDumpTruckWeight)   // delete DumpTruck

                            if (us_container.getChildAt(0) == us_buttonPricepWeight)
                                us_container.removeView(us_buttonPricepWeight)                // delete pricepButton

                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "bus")

                            set_us_button_kind_of_auto(
                                R.string.title_button_bus,
                                R.drawable.ic_directions_bus_black_24dp
                            )

                            if (us_container.getChildAt(0) != us_layoutBus) {  // проверяем нет ли уже layoutBus в контейнере
                                us_container.addView(us_layoutBus)
                                setButtonWidth(this.us_layoutBusButton)
                                setStateButtonUsBusEngine(us_bus_engine)

                            }

                        }
                        "dumpTruck" -> {
                            if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup
                            us_switchButtonLegkGibrid.visibility = GONE

                            if (us_container.getChildAt(0) == us_buttonGruzWeight)
                                us_container.removeView(us_buttonGruzWeight)         // delete gruzcar weight button

                            if (us_container.getChildAt(0) == us_layoutBus)
                                us_container.removeView(us_layoutBus)                // delete layoutBus

                            if (us_container.getChildAt(0) == us_buttonPricepWeight)
                                us_container.removeView(us_buttonPricepWeight)                // delete pricepButton


                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "dumpTruck")
                            us_switchButtonLegkGibrid.visibility = GONE
                            set_us_button_kind_of_auto(
                                R.string.title_button_dumpTruck,
                                R.drawable.ic_dump_truck
                            )

                            if (us_container.getChildAt(0) != us_buttonDumpTruckWeight) {      //проверяем  нет ли уже кнопки dumpTruck

                                us_container.addView(this.us_buttonDumpTruckWeight)  // add weight button for dumpTruck
                                setButtonWidth(this.us_buttonDumpTruckWeight)
                                setStateButtonUsDumpTruckWeight(usDumpTruckWeight)

                            }

                        }
                        "pricep" -> {

                            if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup
                            us_switchButtonLegkGibrid.visibility = GONE

                            if (us_container.getChildAt(0) == us_buttonGruzWeight)
                                us_container.removeView(us_buttonGruzWeight)         // delete gruzcar weight button
                            if (us_container.getChildAt(0) == us_layoutBus)
                                us_container.removeView(us_layoutBus)                // delete layoutBus

                            if (us_container.getChildAt(0) == us_buttonDumpTruckWeight)
                                us_container.removeView(us_buttonDumpTruckWeight)   // delete DumpTruck



                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "pricep")
                            us_switchButtonLegkGibrid.visibility = GONE
                            set_us_button_kind_of_auto(
                                R.string.title_button_pricep,
                                R.drawable.ic_pricep
                            )

                            if (us_container.getChildAt(0) != us_buttonPricepWeight) {      //проверяем  нет ли уже кнопки PricepWeight

                                us_container.addView(this.us_buttonPricepWeight)  // add weight button for pricep
                                setButtonWidth(this.us_buttonPricepWeight)
                                setStateButtonUsPricepWeight(usPricepWeight)

                            }

                        }
                    }

                }// end REQUEST_CODE_KIND_OF_AUTO ==================================================

                REQUEST_CODE_GRUZ_AUTO_WEIGHT -> { // from Activity WeightGruzAutoUs
                    when (data!!.getStringExtra("weight_of_gruz_auto_us")) {
                        "less_2_5t" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_GRUZ_CAR_WEIGHT,
                                "less_2_5t"
                            )
                            set_us_button_gruzWeight(
                                R.string.title_button_less_2_5t_us,
                                R.drawable.ic_hexagon_outline
                            )
                        }
                        "2_5_3_5t" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_GRUZ_CAR_WEIGHT,
                                "2_5_3_5t"
                            )
                            set_us_button_gruzWeight(
                                R.string.title_button_in_2_5_3_5t_us,
                                R.drawable.ic_hexagon_slice_1
                            )
                        }
                        "3_5_5t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "3_5_5t")
                            set_us_button_gruzWeight(
                                R.string.title_button_in_3_5_5t_us,
                                R.drawable.ic_hexagon_slice_2
                            )
                        }
                        "5_8t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "5_8t")
                            set_us_button_gruzWeight(
                                R.string.title_button_in_5_8t_us,
                                R.drawable.ic_hexagon_slice_3
                            )
                        }
                        "8_12t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "8_12t")
                            set_us_button_gruzWeight(
                                R.string.title_button_in_8_12t_us,
                                R.drawable.ic_hexagon_slice_4
                            )
                        }
                        "12_20t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "12_20t")
                            set_us_button_gruzWeight(
                                R.string.title_button_in_12_20t_us,
                                R.drawable.ic_hexagon_slice_5
                            )
                        }
                        "20_50t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "20_50t")
                            set_us_button_gruzWeight(
                                R.string.title_button_in_20_50t_us,
                                R.drawable.ic_hexagon_slice_6
                            )
                        }
                    }
                } // end REQUEST_CODE_GRUZ_AUTO_WEIGHT =============================================

                REQUEST_CODE_BUS_ENGINE -> {
                    when (data!!.getStringExtra("us_engineOfBus")) {
                        "less_2500" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "less_2500")
                            set_us_button_busEngine(
                                R.string.title_button_bus_motor_less_2500,
                                R.drawable.ic_hexagon_outline
                            )
                        }
                        "2500_5000" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "2500_5000")
                            set_us_button_busEngine(
                                R.string.title_button_bus_motor_in_2500_5000,
                                R.drawable.ic_hexagon_slice_2
                            )

                        }
                        "5000_10000" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "5000_10000")
                            set_us_button_busEngine(
                                R.string.title_button_bus_motor_in_5000_10000,
                                R.drawable.ic_hexagon_slice_4
                            )

                        }
                        "more_10000" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "more_10000")
                            set_us_button_busEngine(
                                R.string.title_button_bus_motor_more_10000,
                                R.drawable.ic_hexagon_slice_6
                            )
                        }

                    }

                } // end REQUEST_CODE_BUS_ENGINE
                REQUEST_CODE_DUMPTRUCK_WEIGHT -> {          // from activity DumpTruckWeight
                    when (data!!.getStringExtra("weight_of_dumpTrack_us")) {
                        "50_80t" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT,
                                "50_80t"
                            )
                            set_us_button_dumpTruckWeight(
                                R.string.title_button_in_50_80t_us,
                                R.drawable.ic_hexagon_slice_1)

                        }
                        "80_350t" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT,
                                "80_350t"
                            )
                            set_us_button_dumpTruckWeight(
                                R.string.title_button_in_80_350t_us,
                                R.drawable.ic_hexagon_slice_3)
                        }
                        "more_350t" -> {

                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT,
                                "more_350t"
                            )
                            set_us_button_dumpTruckWeight(
                                R.string.title_button_more_350t_us,
                                R.drawable.ic_hexagon_slice_6)
                        }
                    }

                }

                REQUEST_CODE_PRICEP_WEIGHT -> {          // from activity PricepWeight
                    when (data!!.getStringExtra("weight_of_pricep_us")) {
                        "more_10t_pricep" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_PRICEP_WEIGHT,
                                "more_10t_pricep"
                            )
                            set_us_button_pricepWeight(
                                R.string.title_button_pricep_more10t_us,
                                R.drawable.ic_hexagon_slice_6)

                        }
                        "more_10t_halfpricep" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_PRICEP_WEIGHT,
                                "more_10t_halfpricep"
                            )
                            set_us_button_pricepWeight(
                                R.string.title_button_half_pricep_more10t_us,
                                R.drawable.ic_hexagon_slice_3)
                        }

                    }
                }

            }

        }
    }

    private fun set_us_button_kind_of_auto(titleButtonLegkCar: Int, iconButtonLegkCar: Int) {
        us_buttonKindOfAuto.text = resources.getString(titleButtonLegkCar)
        us_buttonKindOfAuto.setIconResource(iconButtonLegkCar)

    }

    private fun set_us_button_gruzWeight(titleButton: Int, iconButton: Int) {
        us_buttonGruzWeight.text = resources.getString(titleButton)
        us_buttonGruzWeight.gravity = TEXT_ALIGNMENT_VIEW_END
        us_buttonGruzWeight.setIconResource(iconButton)
    }

    private fun set_us_button_busEngine(titleButton: Int, iconButton: Int) {
        us_layoutBusButton.text = resources.getString(titleButton)
        us_layoutBusButton.gravity = TEXT_ALIGNMENT_VIEW_END
        us_layoutBusButton.setIconResource(iconButton)
    }

    private fun set_us_button_dumpTruckWeight(titleButton: Int, iconButton: Int) {
        us_buttonDumpTruckWeight.text = resources.getString(titleButton)
        us_buttonDumpTruckWeight.gravity = TEXT_ALIGNMENT_VIEW_END
        us_buttonDumpTruckWeight.setIconResource(iconButton)
    }

    private fun set_us_button_pricepWeight(titleButton: Int, iconButton: Int){
        us_buttonPricepWeight.text = resources.getString(titleButton)
        us_buttonPricepWeight.gravity = TEXT_ALIGNMENT_VIEW_END
        us_buttonPricepWeight.setIconResource(iconButton)
    }



    fun setStartSettingbuttons() {
        when (this.us_kindAuto) {
            "legk_car" -> {
                set_us_button_kind_of_auto(
                    R.string.title_button_legk_car, R.drawable.ic_car_black_24dp
                )
                if (us_legk_car_gibrid_switch) {
                    us_switchButtonLegkGibrid.isChecked = true
                    us_container.addView(us_RadioGroupLegkGibridMotor)   // if swith gibrid is on than add radiogroup

                    when (us_legk_car_gibrid_capacity) {
                        "less_1000" -> us_RadioGroupLegkGibridMotor.check(R.id.less_1000)
                        "in_1000_2000" -> us_RadioGroupLegkGibridMotor.check(R.id.in_1000_2000)
                        "in_2000_3000" -> us_RadioGroupLegkGibridMotor.check(R.id.in_2000_3000)
                        "in_3000_3500" -> us_RadioGroupLegkGibridMotor.check(R.id.in_3000_3500)
                        "more_3500" -> us_RadioGroupLegkGibridMotor.check(R.id.more_3500)
                    }
                }
            }
            "gruz_car" -> {
                us_switchButtonLegkGibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_gruz_car_us,
                    R.drawable.ic_gruz_black_24dp
                )
                us_container.addView(us_buttonGruzWeight)  // add weight button for gruz_auto
                setButtonWidth(us_buttonGruzWeight)
                setStateButtonUsGruzCarWeight(us_gruz_car_weight)


            }
            "bus" -> {
                us_switchButtonLegkGibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_bus,
                    R.drawable.ic_directions_bus_black_24dp
                )
                us_container.addView(us_layoutBus)  // add layoutBus
                setButtonWidth(us_layoutBusButton)
                setStateButtonUsBusEngine(us_bus_engine)

            }
            "dumpTruck" -> {
                us_switchButtonLegkGibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_dumpTruck,
                    R.drawable.ic_dump_truck
                )
                us_container.addView(this.us_buttonDumpTruckWeight)  // add weight button for dumpTruck
                setButtonWidth(this.us_buttonDumpTruckWeight)
                setStateButtonUsDumpTruckWeight(usDumpTruckWeight)


            }
            "pricep" -> {
                us_switchButtonLegkGibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_pricep,
                    R.drawable.ic_pricep
                )

                us_container.addView(this.us_buttonPricepWeight)  // add weight button for pricep
                setButtonWidth(this.us_buttonPricepWeight)
                setStateButtonUsDumpTruckWeight(usPricepWeight)
            }

        }
    }

    private fun setStateButtonUsGruzCarWeight(usGruzCarWeight: String) {

        when (usGruzCarWeight) {
            "less_2_5t" -> {
                set_us_button_gruzWeight(
                    R.string.title_button_less_2_5t_us,
                    R.drawable.ic_hexagon_outline
                )
            }
            "2_5_3_5t" -> {
                set_us_button_gruzWeight(
                    R.string.title_button_in_2_5_3_5t_us,
                    R.drawable.ic_hexagon_slice_1
                )
            }
            "3_5_5t" -> {
                set_us_button_gruzWeight(
                    R.string.title_button_in_3_5_5t_us,
                    R.drawable.ic_hexagon_slice_2
                )
            }
            "5_8t" -> {
                set_us_button_gruzWeight(
                    R.string.title_button_in_5_8t_us,
                    R.drawable.ic_hexagon_slice_3
                )
            }
            "8_12t" -> {
                set_us_button_gruzWeight(
                    R.string.title_button_in_8_12t_us,
                    R.drawable.ic_hexagon_slice_4
                )
            }
            "12_20t" -> {
                set_us_button_gruzWeight(
                    R.string.title_button_in_12_20t_us,
                    R.drawable.ic_hexagon_slice_5
                )
            }
            "20_50t" -> {
                set_us_button_gruzWeight(
                    R.string.title_button_in_20_50t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }

    private fun setStateButtonUsBusEngine(usBusEngine: String) {

        when (usBusEngine) {
            "less_2500" -> {
                set_us_button_busEngine(
                    R.string.title_button_bus_motor_less_2500,
                    R.drawable.ic_hexagon_outline
                )
            }
            "2500_5000" -> {
                set_us_button_busEngine(
                    R.string.title_button_bus_motor_in_2500_5000,
                    R.drawable.ic_hexagon_slice_2
                )
            }
            "5000_10000" -> {
                set_us_button_busEngine(
                    R.string.title_button_bus_motor_in_5000_10000,
                    R.drawable.ic_hexagon_slice_4
                )
            }
            "more_10000" -> {
                set_us_button_busEngine(
                    R.string.title_button_bus_motor_more_10000,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }

    private fun setStateButtonUsDumpTruckWeight(usDumpTruckWeight: String) {
        when (usDumpTruckWeight) {

            "50_80t" -> {
                set_us_button_busEngine(
                    R.string.title_button_in_50_80t_us,
                    R.drawable.ic_hexagon_slice_1
                )
            }
            "80_350t" -> {
                set_us_button_busEngine(
                    R.string.title_button_in_80_350t_us,
                    R.drawable.ic_hexagon_slice_3
                )
            }
            "more_350t" -> {
                set_us_button_busEngine(
                    R.string.title_button_more_350t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }

    private fun setStateButtonUsPricepWeight(usPricepWeight: String) {
        when (usPricepWeight) {

            "more_10t_pricep" -> {
                set_us_button_pricepWeight(
                    R.string.title_button_pricep_more10t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
            "80_350t" -> {
                set_us_button_pricepWeight(
                    R.string.title_button_half_pricep_more10t_us,
                    R.drawable.ic_hexagon_slice_3
                )
            }

        }
    }


}