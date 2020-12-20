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
import kotlinx.android.synthetic.main.fragment_util_sbor.*

class FragmentUtilSbor : Fragment() {

   // val LOG_TAG = "myLogs"

    private val LAYOUT: Int = R.layout.fragment_util_sbor

    private val color: String = "привет"
    private val widthScreenDPI: String = ""

    private var usAge: Int = 1  // 1 - до трех лет, 2 - от трех до семи лет, 3 - старше семи лет,
    private var usKindAuto: String = "legk_car"

    private var usLegkCarGibridCapacity =
        "less_1000"   // if toogle gibrid car = false, than null.
    private var usLegkCarWeight = "electro"
    private var usGruzCarWeight = "less_2_5t"
    private var usBusEngine = "less_2500"
    private var usBusEngineTemp = ""
    private var usDumpTruckWeight = "50_80t"
    private var usPricepWeight = "more_10t_pricep"


    private lateinit var usTextViewSumsValue: TextView
    private lateinit var usButtonToggleGroupVozrast: MaterialButtonToggleGroup
    private lateinit var usButtonBefore3Years: MaterialButton
    private lateinit var usButtonIn3To7Years: MaterialButton
    private lateinit var usButton7YearsAndOldest: MaterialButton
    private lateinit var usButtonKindOfAuto: MaterialButton

    private lateinit var usContainer: FrameLayout
    private lateinit var usButtonLegkWeight: MaterialButton
    private lateinit var usLayoutLegk: LinearLayout
    private lateinit var usRadioGroupLegkGibridMotor: RadioGroup
    private lateinit var usButtonGruzWeight: MaterialButton
    private lateinit var usLayoutBus: LinearLayout
    private lateinit var usLayoutBusButton: MaterialButton
    private lateinit var usLayoutBusSwitchButton: SwitchMaterial
    private lateinit var usButtonDumpTruckWeight: MaterialButton
    private lateinit var usButtonPricepWeight: MaterialButton

    private lateinit var model: ViewModelUtilSbor
    private lateinit var sprefUtilSbor: SharedPreferences


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
    ): View {
        val view: View = inflater.inflate(LAYOUT, container, false)

        model = ViewModelProviders.of(this).get(ViewModelUtilSbor::class.java)

        usTextViewSumsValue = view.findViewById(R.id.us_textView_Sums_Value)

        initSpref()
        usContainer = view.findViewById(R.id.container_us)
        usButtonKindOfAuto = view.findViewById(R.id.us_button_kind_of_auto)
        usButtonToggleGroupVozrast = view.findViewById(R.id.us_buttonToggleGroup_vozrast)
        usButtonBefore3Years = view.findViewById(R.id.us_button_before_3_years)
        usButtonIn3To7Years = view.findViewById(R.id.us_button_in_3_7_years)
        usButton7YearsAndOldest = view.findViewById(R.id.us_button_7_years_and_oldest)

        this.usLayoutLegk = layoutInflater.inflate(R.layout.us_layout_legk, null) as LinearLayout
        this.usRadioGroupLegkGibridMotor =
            usLayoutLegk.findViewById(R.id.radioGroup_us_legk_gibrid_motor)
        this.usButtonLegkWeight = usLayoutLegk.findViewById(R.id.us_button_legk_weight)

        this.usButtonGruzWeight =
            layoutInflater.inflate(R.layout.us_button_gruz_weight, null) as MaterialButton

        this.usLayoutBus = layoutInflater.inflate(R.layout.us_layout_bus, null) as LinearLayout
        this.usLayoutBusButton = usLayoutBus.findViewById(R.id.us_button_bus_engine)
        this.usLayoutBusSwitchButton = usLayoutBus.findViewById(R.id.us_switch_button_bus)

        this.usButtonDumpTruckWeight =
            layoutInflater.inflate(R.layout.us_button_dumptruck_weight, null) as MaterialButton

        this.usButtonPricepWeight =
            layoutInflater.inflate(R.layout.us_button_pricep_weight, null) as MaterialButton

        setbuttonToggleGroup(
            view,
            R.id.us_buttonToggleGroup_vozrast,
            usButtonBefore3Years.id,
            usButtonIn3To7Years.id,
            usButton7YearsAndOldest.id
        )

        setButtonWidth(usButtonKindOfAuto)


        //========начальные состояния buttons=========================
        //==========================================================================================
        setStartSettingButtons()
        //==========================================================================================


        val onButtonClickListener: OnClickListener = OnClickListener { v ->
            when (v.id) {
                R.id.us_button_kind_of_auto -> {
                    val intent = Intent(activity, ActivityKindOfAutoUS::class.java)
                    startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO)
                }
                R.id.us_button_before_3_years -> {
                    usButtonBefore3Years.isChecked = true
                    model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_AGE, 1)
                }
                R.id.us_button_in_3_7_years -> {
                    us_button_in_3_7_years.isChecked = true
                    model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_AGE, 2)
                }
                R.id.us_button_7_years_and_oldest -> {
                    usButton7YearsAndOldest.isChecked = true
                    model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_AGE, 3)
                }

                R.id.us_button_legk_weight -> {
                    val intent = Intent(activity, ActivityWeightLegkAutoUs::class.java)
                    startActivityForResult(intent, REQUEST_CODE_LEGK_AUTO_WEIGHT)
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

                R.id.us_button_pricep_weight -> {
                    val intent = Intent(activity, ActivityWeightPricepUs::class.java)
                    startActivityForResult(intent, REQUEST_CODE_PRICEP_WEIGHT)
                }
            }
        }
        usButtonBefore3Years.setOnClickListener(onButtonClickListener)
        usButtonIn3To7Years.setOnClickListener(onButtonClickListener)
        usButton7YearsAndOldest.setOnClickListener(onButtonClickListener)
        usButtonKindOfAuto.setOnClickListener(onButtonClickListener)
        usButtonLegkWeight.setOnClickListener(onButtonClickListener)
        usButtonGruzWeight.setOnClickListener(onButtonClickListener)
        usLayoutBusButton.setOnClickListener(onButtonClickListener)
        usButtonDumpTruckWeight.setOnClickListener(onButtonClickListener)
        usButtonPricepWeight.setOnClickListener(onButtonClickListener)


        usRadioGroupLegkGibridMotor.setOnCheckedChangeListener { _, checkedId ->
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
        usLayoutBusSwitchButton.setOnCheckedChangeListener { _, isChecked ->
            val layoutParam: ViewGroup.LayoutParams = usLayoutBusSwitchButton.layoutParams
            if (isChecked) {
                usBusEngineTemp =
                    this.usBusEngine        // сохраняем состояние кнопки чтобы когда false передать во Preference нужное значение
                model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "electro")
                usLayoutBusButton.visibility = GONE
                usLayoutBusSwitchButton.layoutParams = layoutParam
                setSwitchButtonWidth(usLayoutBusSwitchButton)
            } else {
                usLayoutBusButton.visibility = VISIBLE
                when (usBusEngineTemp) {
                    "less_2500" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "less_2500")
                        setUsButtonBusengine(
                            R.string.title_button_bus_motor_less_2500,
                            R.drawable.ic_hexagon_outline
                        )
                    }
                    "2500_5000" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "2500_5000")
                        setUsButtonBusengine(
                            R.string.title_button_bus_motor_in_2500_5000,
                            R.drawable.ic_hexagon_slice_2
                        )
                    }
                    "5000_10000" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "5000_10000")
                        setUsButtonBusengine(
                            R.string.title_button_bus_motor_in_5000_10000,
                            R.drawable.ic_hexagon_slice_4
                        )
                    }
                    "more_10000" -> {
                        model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "more_10000")
                        setUsButtonBusengine(
                            R.string.title_button_bus_motor_more_10000,
                            R.drawable.ic_hexagon_slice_6
                        )
                    }
                }
            }
        }

        return view
    }

    private fun setbuttonToggleGroup(
        view: View,
        idButtonToogleGroup: Int,
        idButton1: Int,
        idButton2: Int,
        idButton3: Int
    ) {

        val buttonToogleGroup: MaterialButtonToggleGroup = view.findViewById(idButtonToogleGroup)

        val button1: MaterialButton = view.findViewById(idButton1)
        button1.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) / 3 - 25   // устанавливаем ширину кнопки по размеру экрана

        val button2: MaterialButton = view.findViewById(idButton2)
        button2.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) / 3 - 40  // устанавливаем ширину кнопки по размеру экрана

        val button3: MaterialButton = view.findViewById(idButton3)
        button3.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) / 3 - 10      // устанавливаем ширину кнопки по размеру экрана

        // устанавливаем начальное состояние кнопок возраст

        when (usAge) {
            1 -> buttonToogleGroup.check(idButton1)   // до трех лет
            2 -> buttonToogleGroup.check(idButton2)   // от трех до семи лет
            3 -> buttonToogleGroup.check(idButton3)   // 7 лет и старше
        }
    }

    private fun setButtonWidth(button: MaterialButton) {
        button.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) - 80   // устанавливаем ширину кнопки без группы
    }

    private fun setSwitchButtonWidth(switchButton: SwitchMaterial) {
        switchButton.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) - 80   // устанавливаем ширину switchButton без группы
    }

    private fun initSpref() {
        sprefUtilSbor = context!!.getSharedPreferences(SPREF_UTILSBOR_NAME, Context.MODE_PRIVATE)
        if (sprefUtilSbor.contains(SPREF_UTILSBOR_AGE)) {
            this.usKindAuto = sprefUtilSbor.getString(SPREF_UTILSBOR_KINDAUTO, "")
            this.usAge = sprefUtilSbor.getInt(SPREF_UTILSBOR_AGE, 0)
            this.usLegkCarGibridCapacity =
                sprefUtilSbor.getString(SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY, "")
            this.usLegkCarWeight = sprefUtilSbor.getString(SPREF_UTILSBOR_LEGK_CAR_WEIGHT, "")
            this.usGruzCarWeight = sprefUtilSbor.getString(SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "")
            this.usBusEngine = sprefUtilSbor.getString(SPREF_UTILSBOR_BUS_ENGINE, "")
            this.usDumpTruckWeight = sprefUtilSbor.getString(SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT, "")
            this.usPricepWeight = sprefUtilSbor.getString(SPREF_UTILSBOR_PRICEP_WEIGHT, "")
        } else {
            val editor: SharedPreferences.Editor = sprefUtilSbor.edit()
            editor.putString(SPREF_UTILSBOR_KINDAUTO, usKindAuto)
            editor.putInt(SPREF_UTILSBOR_AGE, usAge)
            editor.putString(SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY, usLegkCarGibridCapacity)
            editor.putString(SPREF_UTILSBOR_LEGK_CAR_WEIGHT, usLegkCarWeight)
            editor.putString(SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, usGruzCarWeight)
            editor.putString(SPREF_UTILSBOR_BUS_ENGINE, usBusEngine)
            editor.putString(SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT, usDumpTruckWeight)
            editor.putString(SPREF_UTILSBOR_PRICEP_WEIGHT, usPricepWeight)
            editor.apply()
        }

        initPreferencesObserver()
    }

    private fun initPreferencesObserver() {
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_KINDAUTO, "")
            .observe(this, Observer<String> { value ->
                this.usKindAuto = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /* us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            }
            )
        sprefUtilSbor.intLiveData(SPREF_UTILSBOR_AGE, 1)
            .observe(this, Observer<Int> { value ->
                this.usAge = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /*us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            }
            )
        /* sprefUtilSbor.booleanLiveData(SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, false)
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
             })*/
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY, "")
            .observe(this, Observer<String> { value ->
                this.usLegkCarGibridCapacity = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /*us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })

        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_LEGK_CAR_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.usLegkCarWeight = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /* us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })


        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.usGruzCarWeight = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /* us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_BUS_ENGINE, "")
            .observe(this, Observer<String> { value ->
                this.usBusEngine = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /*us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.usDumpTruckWeight = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /*  us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
                        usDumpTruckWeight,
                        usPricepWeight
                    )
            })

        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_PRICEP_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.usPricepWeight = value
                usTextViewSumsValue.text =
                    model.calculateSumsValue(
                        usKindAuto,
                        usAge,
                        /*us_legk_car_gibrid_switch,*/
                        usLegkCarGibridCapacity,
                        usLegkCarWeight,
                        usGruzCarWeight,
                        usBusEngine,
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

                            if (usContainer.getChildAt(0) == usButtonGruzWeight)  // delete gruzcar weight button
                                usContainer.removeView(usButtonGruzWeight)

                            if (usContainer.getChildAt(0) == usLayoutBus)
                                usContainer.removeView(usLayoutBus)   // delete layoutBus

                            if (usContainer.getChildAt(0) == usButtonDumpTruckWeight)
                                usContainer.removeView(usButtonDumpTruckWeight)   // delete DumpTruck

                            if (usContainer.getChildAt(0) == usButtonPricepWeight)
                                usContainer.removeView(usButtonPricepWeight)                // delete pricepButton

                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "legk_car")
                            setUsButtonKindOfAuto(
                                R.string.title_button_legk_car,
                                R.drawable.ic_car_black_24dp
                            )

                            if (usContainer.getChildAt(0) != usLayoutLegk) {

                                usContainer.addView(this.usLayoutLegk)  // add weight button for legk_auto
                                setButtonWidth(this.usButtonLegkWeight)
                                setStateButtonUsLegkCarWeight(usLegkCarWeight)

                                if (usLegkCarWeight == "gibrid") {
                                    usRadioGroupLegkGibridMotor.visibility = VISIBLE
                                    when (usLegkCarGibridCapacity) {
                                        "less_1000" -> usRadioGroupLegkGibridMotor.check(R.id.less_1000)
                                        "in_1000_2000" -> usRadioGroupLegkGibridMotor.check(R.id.in_1000_2000)
                                        "in_2000_3000" -> usRadioGroupLegkGibridMotor.check(R.id.in_2000_3000)
                                        "in_3000_3500" -> usRadioGroupLegkGibridMotor.check(R.id.in_3000_3500)
                                        "more_3500" -> usRadioGroupLegkGibridMotor.check(R.id.more_3500)
                                    }
                                }
                            }
                        }
                        "gruz_car" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "gruz_car")
                            setUsButtonKindOfAuto(
                                R.string.title_button_gruz_car_us,
                                R.drawable.ic_gruz_black_24dp
                            )
                            /* us_switchButtonLegkGibrid.visibility = GONE*/

                            if (usContainer.getChildAt(0) == usLayoutLegk)
                                usContainer.removeView(usLayoutLegk)   // delete legkcar gibridRadiogroup

                            /*        if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                        us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup*/

                            if (usContainer.getChildAt(0) == usLayoutBus)
                                usContainer.removeView(usLayoutBus)                // delete layoutBus

                            if (usContainer.getChildAt(0) == usButtonDumpTruckWeight)
                                usContainer.removeView(usButtonDumpTruckWeight)   // delete DumpTruck

                            if (usContainer.getChildAt(0) == usButtonPricepWeight)
                                usContainer.removeView(usButtonPricepWeight)                // delete pricepButton

                            if (usContainer.getChildAt(0) != usButtonGruzWeight) {      //проверяем  нет ли уже кнопки gruz_weight

                                usContainer.addView(this.usButtonGruzWeight)  // add weight button for gruz_auto
                                setButtonWidth(this.usButtonGruzWeight)
                                setStateButtonUsGruzCarWeight(usGruzCarWeight)

                            }

                        }
                        "bus" -> {

                            if (usContainer.getChildAt(0) == usLayoutLegk)
                                usContainer.removeView(usLayoutLegk)   // delete legkcar gibridRadiogroup

                            /*  if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                  us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup*/
                            /* us_switchButtonLegkGibrid.visibility = GONE*/

                            if (usContainer.getChildAt(0) == usButtonGruzWeight)
                                usContainer.removeView(usButtonGruzWeight)         // delete gruzcar weight button

                            if (usContainer.getChildAt(0) == usButtonDumpTruckWeight)
                                usContainer.removeView(usButtonDumpTruckWeight)   // delete DumpTruck

                            if (usContainer.getChildAt(0) == usButtonPricepWeight)
                                usContainer.removeView(usButtonPricepWeight)                // delete pricepButton

                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "bus")

                            setUsButtonKindOfAuto(
                                R.string.title_button_bus,
                                R.drawable.ic_directions_bus_black_24dp
                            )

                            if (usContainer.getChildAt(0) != usLayoutBus) {  // проверяем нет ли уже layoutBus в контейнере
                                usContainer.addView(usLayoutBus)
                                setButtonWidth(this.usLayoutBusButton)
                                setStateButtonUsBusEngine(usBusEngine)

                            }

                        }
                        "dumpTruck" -> {

                            if (usContainer.getChildAt(0) == usLayoutLegk)
                                usContainer.removeView(usLayoutLegk)   // delete legkcar gibridRadiogroup

                            /*if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup*/
                            /*  us_switchButtonLegkGibrid.visibility = GONE*/

                            if (usContainer.getChildAt(0) == usButtonGruzWeight)
                                usContainer.removeView(usButtonGruzWeight)         // delete gruzcar weight button

                            if (usContainer.getChildAt(0) == usLayoutBus)
                                usContainer.removeView(usLayoutBus)                // delete layoutBus

                            if (usContainer.getChildAt(0) == usButtonPricepWeight)
                                usContainer.removeView(usButtonPricepWeight)                // delete pricepButton


                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "dumpTruck")
                            /*  us_switchButtonLegkGibrid.visibility = GONE*/
                            setUsButtonKindOfAuto(
                                R.string.title_button_dumpTruck,
                                R.drawable.ic_dump_truck
                            )

                            if (usContainer.getChildAt(0) != usButtonDumpTruckWeight) {      //проверяем  нет ли уже кнопки dumpTruck

                                usContainer.addView(this.usButtonDumpTruckWeight)  // add weight button for dumpTruck
                                setButtonWidth(this.usButtonDumpTruckWeight)
                                setStateButtonUsDumpTruckWeight(usDumpTruckWeight)

                            }

                        }
                        "pricep" -> {

                            if (usContainer.getChildAt(0) == usLayoutLegk)
                                usContainer.removeView(usLayoutLegk)   // delete legkcar gibridRadiogroup

                            /* if (us_container.getChildAt(0) == us_RadioGroupLegkGibridMotor)
                                 us_container.removeView(us_RadioGroupLegkGibridMotor)   // delete legkcar gibridRadiogroup*/
                            /*us_switchButtonLegkGibrid.visibility = GONE*/

                            if (usContainer.getChildAt(0) == usButtonGruzWeight)
                                usContainer.removeView(usButtonGruzWeight)         // delete gruzcar weight button
                            if (usContainer.getChildAt(0) == usLayoutBus)
                                usContainer.removeView(usLayoutBus)                // delete layoutBus

                            if (usContainer.getChildAt(0) == usButtonDumpTruckWeight)
                                usContainer.removeView(usButtonDumpTruckWeight)   // delete DumpTruck


                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "pricep")
                            /* us_switchButtonLegkGibrid.visibility = GONE*/
                            setUsButtonKindOfAuto(
                                R.string.title_button_pricep,
                                R.drawable.ic_pricep
                            )

                            if (usContainer.getChildAt(0) != usButtonPricepWeight) {      //проверяем  нет ли уже кнопки PricepWeight

                                usContainer.addView(this.usButtonPricepWeight)  // add weight button for pricep
                                setButtonWidth(this.usButtonPricepWeight)
                                setStateButtonUsPricepWeight(usPricepWeight)

                            }

                        }
                    }

                }// end REQUEST_CODE_KIND_OF_AUTO ==================================================

                REQUEST_CODE_LEGK_AUTO_WEIGHT -> {
                    when (data!!.getStringExtra("weight_of_legk_auto_us")) {
                        "electro" -> {

                            usRadioGroupLegkGibridMotor.visibility = GONE
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_LEGK_CAR_WEIGHT,
                                "electro"
                            )
                            setUsButtonLegkweight(
                                R.string.title_button_legk_electro_us,
                                R.drawable.ic_hexagon_slice_1
                            )
                        }
                        "gibrid" -> {

                            usRadioGroupLegkGibridMotor.visibility = VISIBLE

                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_LEGK_CAR_WEIGHT,
                                "gibrid"
                            )
                            setUsButtonLegkweight(
                                R.string.title_button_legk_gibrid_us,
                                R.drawable.ic_hexagon_slice_2
                            )

                        }
                        "fiz" -> {
                            usRadioGroupLegkGibridMotor.visibility = GONE
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_LEGK_CAR_WEIGHT,
                                "fiz"
                            )
                            setUsButtonLegkweight(
                                R.string.title_button_legk_fiz_us,
                                R.drawable.ic_hexagon_slice_4
                            )
                        }
                        "ees" -> {
                            usRadioGroupLegkGibridMotor.visibility = GONE
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_LEGK_CAR_WEIGHT,
                                "ees"
                            )
                            setUsButtonLegkweight(
                                R.string.title_button_legk_ees_us,
                                R.drawable.ic_hexagon_slice_6
                            )
                        }
                    }
                } // end REQUEST_CODE_LEGK_AUTO_WEIGHT =============================================

                REQUEST_CODE_GRUZ_AUTO_WEIGHT -> { // from Activity WeightGruzAutoUs
                    when (data!!.getStringExtra("weight_of_gruz_auto_us")) {
                        "less_2_5t" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_GRUZ_CAR_WEIGHT,
                                "less_2_5t"
                            )
                            setUsButtonGruzweight(
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
                            setUsButtonGruzweight(
                                R.string.title_button_in_2_5_3_5t_us,
                                R.drawable.ic_hexagon_slice_1
                            )
                        }
                        "3_5_5t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "3_5_5t")
                            setUsButtonGruzweight(
                                R.string.title_button_in_3_5_5t_us,
                                R.drawable.ic_hexagon_slice_2
                            )
                        }
                        "5_8t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "5_8t")
                            setUsButtonGruzweight(
                                R.string.title_button_in_5_8t_us,
                                R.drawable.ic_hexagon_slice_3
                            )
                        }
                        "8_12t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "8_12t")
                            setUsButtonGruzweight(
                                R.string.title_button_in_8_12t_us,
                                R.drawable.ic_hexagon_slice_4
                            )
                        }
                        "12_20t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "12_20t")
                            setUsButtonGruzweight(
                                R.string.title_button_in_12_20t_us,
                                R.drawable.ic_hexagon_slice_5
                            )
                        }
                        "20_30t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "20_30t")
                            setUsButtonGruzweight(
                                R.string.title_button_in_20_30t_us,
                                R.drawable.ic_hexagon_slice_6
                            )
                        }
                        "30_50t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "30_50t")
                            setUsButtonGruzweight(
                                R.string.title_button_in_30_50t_us,
                                R.drawable.ic_hexagon_slice_6
                            )
                        }
                    }
                } // end REQUEST_CODE_GRUZ_AUTO_WEIGHT =============================================

                REQUEST_CODE_BUS_ENGINE -> {
                    when (data!!.getStringExtra("us_engineOfBus")) {
                        "less_2500" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "less_2500")
                            setUsButtonBusengine(
                                R.string.title_button_bus_motor_less_2500,
                                R.drawable.ic_hexagon_outline
                            )
                        }
                        "2500_5000" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "2500_5000")
                            setUsButtonBusengine(
                                R.string.title_button_bus_motor_in_2500_5000,
                                R.drawable.ic_hexagon_slice_2
                            )

                        }
                        "5000_10000" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "5000_10000")
                            setUsButtonBusengine(
                                R.string.title_button_bus_motor_in_5000_10000,
                                R.drawable.ic_hexagon_slice_4
                            )

                        }
                        "more_10000" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_BUS_ENGINE, "more_10000")
                            setUsButtonBusengine(
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
                            setUsButtonDumptruckweight(
                                R.string.title_button_in_50_80t_us,
                                R.drawable.ic_hexagon_slice_1
                            )

                        }
                        "80_350t" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT,
                                "80_350t"
                            )
                            setUsButtonDumptruckweight(
                                R.string.title_button_in_80_350t_us,
                                R.drawable.ic_hexagon_slice_3
                            )
                        }
                        "more_350t" -> {

                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT,
                                "more_350t"
                            )
                            setUsButtonDumptruckweight(
                                R.string.title_button_more_350t_us,
                                R.drawable.ic_hexagon_slice_6
                            )
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
                            setUsButtonPricepweight(
                                R.string.title_button_pricep_more10t_us,
                                R.drawable.ic_hexagon_slice_6
                            )

                        }
                        "more_10t_halfpricep" -> {
                            model.putSprefs(
                                sprefUtilSbor,
                                SPREF_UTILSBOR_PRICEP_WEIGHT,
                                "more_10t_halfpricep"
                            )
                            setUsButtonPricepweight(
                                R.string.title_button_half_pricep_more10t_us,
                                R.drawable.ic_hexagon_slice_3
                            )
                        }

                    }
                }

            }

        }
    }

    private fun setUsButtonKindOfAuto(titleButtonLegkCar: Int, iconButtonLegkCar: Int) {
        usButtonKindOfAuto.text = resources.getString(titleButtonLegkCar)
        usButtonKindOfAuto.setIconResource(iconButtonLegkCar)

    }

    private fun setUsButtonLegkweight(titleButton: Int, iconButton: Int) {
        usButtonLegkWeight.text = resources.getString(titleButton)
        usButtonLegkWeight.gravity = TEXT_ALIGNMENT_VIEW_END
        usButtonLegkWeight.setIconResource(iconButton)
    }

    private fun setUsButtonGruzweight(titleButton: Int, iconButton: Int) {
        usButtonGruzWeight.text = resources.getString(titleButton)
        usButtonGruzWeight.gravity = TEXT_ALIGNMENT_VIEW_END
        usButtonGruzWeight.setIconResource(iconButton)
    }

    private fun setUsButtonBusengine(titleButton: Int, iconButton: Int) {
        usLayoutBusButton.text = resources.getString(titleButton)
        usLayoutBusButton.gravity = TEXT_ALIGNMENT_VIEW_END
        usLayoutBusButton.setIconResource(iconButton)
    }

    private fun setUsButtonDumptruckweight(titleButton: Int, iconButton: Int) {
        usButtonDumpTruckWeight.text = resources.getString(titleButton)
        usButtonDumpTruckWeight.gravity = TEXT_ALIGNMENT_VIEW_END
        usButtonDumpTruckWeight.setIconResource(iconButton)
    }

    private fun setUsButtonPricepweight(titleButton: Int, iconButton: Int) {
        usButtonPricepWeight.text = resources.getString(titleButton)
        usButtonPricepWeight.gravity = TEXT_ALIGNMENT_VIEW_END
        usButtonPricepWeight.setIconResource(iconButton)
    }


    private fun setStartSettingButtons() {
        when (this.usKindAuto) {
            "legk_car" -> {
                setUsButtonKindOfAuto(
                    R.string.title_button_legk_car, R.drawable.ic_car_black_24dp
                )
                usContainer.addView(usLayoutLegk)  // add lauout legk_auto
                setButtonWidth(usButtonLegkWeight)
                setStateButtonUsLegkCarWeight(usLegkCarWeight)

                if (usLegkCarWeight == "gibrid") {
                    usRadioGroupLegkGibridMotor.visibility = VISIBLE
                    when (usLegkCarGibridCapacity) {
                        "less_1000" -> usRadioGroupLegkGibridMotor.check(R.id.less_1000)
                        "in_1000_2000" -> usRadioGroupLegkGibridMotor.check(R.id.in_1000_2000)
                        "in_2000_3000" -> usRadioGroupLegkGibridMotor.check(R.id.in_2000_3000)
                        "in_3000_3500" -> usRadioGroupLegkGibridMotor.check(R.id.in_3000_3500)
                        "more_3500" -> usRadioGroupLegkGibridMotor.check(R.id.more_3500)
                    }
                } else usRadioGroupLegkGibridMotor.visibility = GONE


            }

            "gruz_car" -> {
                /* us_switchButtonLegkGibrid.visibility = GONE*/
                setUsButtonKindOfAuto(
                    R.string.title_button_gruz_car_us,
                    R.drawable.ic_gruz_black_24dp
                )
                usContainer.addView(usButtonGruzWeight)  // add weight button for gruz_auto
                setButtonWidth(usButtonGruzWeight)
                setStateButtonUsGruzCarWeight(usGruzCarWeight)


            }
            "bus" -> {
                /*us_switchButtonLegkGibrid.visibility = GONE*/
                setUsButtonKindOfAuto(
                    R.string.title_button_bus,
                    R.drawable.ic_directions_bus_black_24dp
                )
                usContainer.addView(usLayoutBus)  // add layoutBus
                setButtonWidth(usLayoutBusButton)
                setStateButtonUsBusEngine(usBusEngine)

            }
            "dumpTruck" -> {
                /*us_switchButtonLegkGibrid.visibility = GONE*/
                setUsButtonKindOfAuto(
                    R.string.title_button_dumpTruck,
                    R.drawable.ic_dump_truck
                )
                usContainer.addView(this.usButtonDumpTruckWeight)  // add weight button for dumpTruck
                setButtonWidth(this.usButtonDumpTruckWeight)
                setStateButtonUsDumpTruckWeight(usDumpTruckWeight)


            }
            "pricep" -> {
                /* us_switchButtonLegkGibrid.visibility = GONE*/
                setUsButtonKindOfAuto(
                    R.string.title_button_pricep,
                    R.drawable.ic_pricep
                )

                usContainer.addView(this.usButtonPricepWeight)  // add weight button for pricep
                setButtonWidth(this.usButtonPricepWeight)
                setStateButtonUsDumpTruckWeight(usPricepWeight)
            }

        }
    }

    private fun setStateButtonUsLegkCarWeight(usLegkCarWeight: String) {
        when (usLegkCarWeight) {
            "electro" -> {
                setUsButtonLegkweight(
                    R.string.title_button_legk_electro_us,
                    R.drawable.ic_hexagon_outline
                )
            }
            "gibrid" -> {
                setUsButtonLegkweight(
                    R.string.title_button_legk_gibrid_us,
                    R.drawable.ic_hexagon_slice_2
                )
            }
            "fiz" -> {
                setUsButtonLegkweight(
                    R.string.title_button_legk_fiz_us,
                    R.drawable.ic_hexagon_slice_4
                )
            }
            "ees" -> {
                setUsButtonLegkweight(
                    R.string.title_button_legk_ees_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }

    private fun setStateButtonUsGruzCarWeight(usGruzCarWeight: String) {

        when (usGruzCarWeight) {
            "less_2_5t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_less_2_5t_us,
                    R.drawable.ic_hexagon_outline
                )
            }
            "2_5_3_5t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_in_2_5_3_5t_us,
                    R.drawable.ic_hexagon_slice_1
                )
            }
            "3_5_5t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_in_3_5_5t_us,
                    R.drawable.ic_hexagon_slice_2
                )
            }
            "5_8t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_in_5_8t_us,
                    R.drawable.ic_hexagon_slice_3
                )
            }
            "8_12t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_in_8_12t_us,
                    R.drawable.ic_hexagon_slice_4
                )
            }
            "12_20t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_in_12_20t_us,
                    R.drawable.ic_hexagon_slice_5
                )
            }
            "20_30t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_in_20_30t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
            "30_50t" -> {
                setUsButtonGruzweight(
                    R.string.title_button_in_30_50t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }

    private fun setStateButtonUsBusEngine(usBusEngine: String) {

        when (usBusEngine) {
            "less_2500" -> {
                setUsButtonBusengine(
                    R.string.title_button_bus_motor_less_2500,
                    R.drawable.ic_hexagon_outline
                )
            }
            "2500_5000" -> {
                setUsButtonBusengine(
                    R.string.title_button_bus_motor_in_2500_5000,
                    R.drawable.ic_hexagon_slice_2
                )
            }
            "5000_10000" -> {
                setUsButtonBusengine(
                    R.string.title_button_bus_motor_in_5000_10000,
                    R.drawable.ic_hexagon_slice_4
                )
            }
            "more_10000" -> {
                setUsButtonBusengine(
                    R.string.title_button_bus_motor_more_10000,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }

    private fun setStateButtonUsDumpTruckWeight(usDumpTruckWeight: String) {
        when (usDumpTruckWeight) {

            "50_80t" -> {
                setUsButtonBusengine(
                    R.string.title_button_in_50_80t_us,
                    R.drawable.ic_hexagon_slice_1
                )
            }
            "80_350t" -> {
                setUsButtonBusengine(
                    R.string.title_button_in_80_350t_us,
                    R.drawable.ic_hexagon_slice_3
                )
            }
            "more_350t" -> {
                setUsButtonBusengine(
                    R.string.title_button_more_350t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }

    private fun setStateButtonUsPricepWeight(usPricepWeight: String) {
        when (usPricepWeight) {

            "more_10t_pricep" -> {
                setUsButtonPricepweight(
                    R.string.title_button_pricep_more10t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
            "80_350t" -> {
                setUsButtonPricepweight(
                    R.string.title_button_half_pricep_more10t_us,
                    R.drawable.ic_hexagon_slice_3
                )
            }

        }
    }

companion object {
    const val SPREF_UTILSBOR_NAME = "sprefUtilsbor"
    const val SPREF_UTILSBOR_KINDAUTO = "usKindAuto"
    const val SPREF_UTILSBOR_AGE = "usAge"

    const val SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY = "us_legk_car_gibrid_capacity"
    const val SPREF_UTILSBOR_LEGK_CAR_WEIGHT = "weight_of_legk_auto_us"
    const val SPREF_UTILSBOR_GRUZ_CAR_WEIGHT = "weight_of_gruz_auto_us"
    const val SPREF_UTILSBOR_BUS_ENGINE = "us_engineOfBus"
    const val SPREF_UTILSBOR_DUMP_TRUCK_WEIGHT = "us_dumpTruckWeight"
    const val SPREF_UTILSBOR_PRICEP_WEIGHT = "us_pricepWeight"

    const val REQUEST_CODE_KIND_OF_AUTO = 1
    const val REQUEST_CODE_LEGK_AUTO_WEIGHT = 6
    const val REQUEST_CODE_GRUZ_AUTO_WEIGHT = 2
    const val REQUEST_CODE_BUS_ENGINE = 3
    const val REQUEST_CODE_DUMPTRUCK_WEIGHT = 4
    const val REQUEST_CODE_PRICEP_WEIGHT = 5
}
}