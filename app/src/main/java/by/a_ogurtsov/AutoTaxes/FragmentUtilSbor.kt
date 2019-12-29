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
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.a_ogurtsov.AutoTaxes.viewModels.ViewModelUtilSbor
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.switchmaterial.SwitchMaterial

class FragmentUtilSbor : Fragment() {

    private val LAYOUT: Int = R.layout.fragment_util_sbor


    private val color: String = "привет"
    private val widthScreenDPI: String = ""

    private var us_age: Int = 1            // 1 - до трех лет, 2 - старше трех лет
    private var us_kindAuto: String = "legk_car"
    private var us_legk_car_gibrid_switch = false
    private var us_legk_car_gibrid_capacity =
        "less_1000"   // if toogle gibrid car = false, than null.
    private var us_gruz_car_weight = "less_2_5t"


    private lateinit var us_textView_Sums_Value: TextView
    private lateinit var us_buttonToggleGroup_vozrast: MaterialButtonToggleGroup
    private lateinit var us_button_before_3_years: MaterialButton
    private lateinit var us_button_3_years_and_oldest: MaterialButton
    private lateinit var us_button_kind_of_auto: MaterialButton
    private lateinit var us_switchButton_legk_gibrid: SwitchMaterial
    private lateinit var us_container: FrameLayout
    private lateinit var radioGroup_us_legk_gibrid_motor: RadioGroup
    private lateinit var radioGroup_us_gibrid_motor_item: RadioButton
    private lateinit var us_button_gruz_weight: MaterialButton


    private lateinit var model: ViewModelUtilSbor
    private lateinit var sprefUtilSbor: SharedPreferences

    val SPREF_UTILSBOR_NAME = "sprefUtilsbor"
    val SPREF_UTILSBOR_KINDAUTO = "usKindAuto"
    val SPREF_UTILSBOR_AGE = "usAge"
    val SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH = "us_legk_car_gibrid_switch"
    val SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY = "us_legk_car_gibrid_capacity"
    val SPREF_UTILSBOR_GRUZ_CAR_WEIGHT = "weight_of_gruz_auto_us"

    val REQUEST_CODE_KIND_OF_AUTO = 1
    val REQUEST_CODE_GRUZ_AUTO_WEIGHT = 2


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

        us_textView_Sums_Value = view.findViewById(R.id.us_textView_Sums_Value)

        initSpref()
        us_container = view.findViewById(R.id.container_us)
        us_button_kind_of_auto = view.findViewById(R.id.us_button_kind_of_auto)
        us_buttonToggleGroup_vozrast = view.findViewById(R.id.us_buttonToggleGroup_vozrast)
        us_button_before_3_years = view.findViewById(R.id.us_button_before_3_years)
        us_button_3_years_and_oldest = view.findViewById(R.id.us_button_3_years_and_oldest)
        us_switchButton_legk_gibrid = view.findViewById(R.id.switch_button_gibrid)

        radioGroup_us_legk_gibrid_motor = // radioGroupForLegkAutoGibrid
            layoutInflater.inflate(R.layout.us_gibrid_motor, null) as RadioGroup

        this.us_button_gruz_weight =
            layoutInflater.inflate(R.layout.us_button_gruz_weight, null) as MaterialButton


        setbuttonToggleGroup(
            view,
            R.id.us_buttonToggleGroup_vozrast,
            us_button_before_3_years.id,
            us_button_3_years_and_oldest.id
        )

        setButtonWidth(us_button_kind_of_auto)


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
                    us_button_before_3_years.isChecked = true
                    model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_AGE, 1)

                }
                R.id.us_button_3_years_and_oldest -> {
                    us_button_3_years_and_oldest.isChecked = true
                    model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_AGE, 2)
                }
                R.id.us_button_gruz_weight -> {
                    val intent = Intent(activity, ActivityWeightGruzAutoUs::class.java)
                    startActivityForResult(intent, REQUEST_CODE_GRUZ_AUTO_WEIGHT)
                }

            }
        }
        us_button_before_3_years.setOnClickListener(onButtonClickListener)
        us_button_3_years_and_oldest.setOnClickListener(onButtonClickListener)
        us_button_kind_of_auto.setOnClickListener(onButtonClickListener)
        us_button_gruz_weight.setOnClickListener(onButtonClickListener)

        us_switchButton_legk_gibrid.setOnCheckedChangeListener { _, isChecked ->
            // switch gibrid
            if (isChecked) {
                model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, true)
                us_container.addView(radioGroup_us_legk_gibrid_motor)
                when (us_legk_car_gibrid_capacity) {
                    "less_1000" -> radioGroup_us_legk_gibrid_motor.check(R.id.less_1000)
                    "in_1000_2000" -> radioGroup_us_legk_gibrid_motor.check(R.id.in_1000_2000)
                    "in_2000_3000" -> radioGroup_us_legk_gibrid_motor.check(R.id.in_2000_3000)
                    "in_3000_3500" -> radioGroup_us_legk_gibrid_motor.check(R.id.in_3000_3500)
                    "more_3500" -> radioGroup_us_legk_gibrid_motor.check(R.id.more_3500)
                }

            } else {
                model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, false)
                us_container.removeView(radioGroup_us_legk_gibrid_motor)
            }
        }

        radioGroup_us_legk_gibrid_motor.setOnCheckedChangeListener { radioGroup_us_gibrid_motor, checkedId ->
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

        }
        initPreferencesObserver()

    }

    fun initPreferencesObserver() {
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_KINDAUTO, "")
            .observe(this, Observer<String> { value ->
                this.us_kindAuto = value
                us_textView_Sums_Value.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight
                    )
            }
            )
        sprefUtilSbor.intLiveData(SPREF_UTILSBOR_AGE, 1)
            .observe(this, Observer<Int> { value ->
                this.us_age = value
                us_textView_Sums_Value.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight
                    )
            }
            )
        sprefUtilSbor.booleanLiveData(SPREF_UTILSBOR_LEGK_CAR_GIBRID_SWITCH, false)
            .observe(this, Observer<Boolean> { value ->
                this.us_legk_car_gibrid_switch = value
                us_textView_Sums_Value.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_LEGK_CAR_GIBRID_CAPACITY, "")
            .observe(this, Observer<String> { value ->
                this.us_legk_car_gibrid_capacity = value
                us_textView_Sums_Value.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight
                    )
            })
        sprefUtilSbor.stringLiveData(SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "")
            .observe(this, Observer<String> { value ->
                this.us_gruz_car_weight = value
                us_textView_Sums_Value.text =
                    model.calculateSumsValue(
                        us_kindAuto,
                        us_age,
                        us_legk_car_gibrid_switch,
                        us_legk_car_gibrid_capacity,
                        us_gruz_car_weight
                    )
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_KIND_OF_AUTO -> {              // ====================================
                    when (data!!.getStringExtra("kind_of_auto")) {
                        "legk_car" -> {

                            if (us_container.getChildAt(0) == us_button_gruz_weight)
                                us_container.removeView(us_button_gruz_weight)

                            us_switchButton_legk_gibrid.visibility =
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
                            us_switchButton_legk_gibrid.isChecked = false


                        }
                        "gruz_car" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "gruz_car")
                            set_us_button_kind_of_auto(
                                R.string.title_button_gruz_car_us,
                                R.drawable.ic_gruz_black_24dp
                            )
                            us_switchButton_legk_gibrid.visibility = GONE

                            if (us_container.getChildAt(0) == radioGroup_us_legk_gibrid_motor)
                                us_container.removeView(radioGroup_us_legk_gibrid_motor)   // delete legkcar gibridRadiogroup

                            if (us_container.getChildAt(0) != us_button_gruz_weight) {      //проверяем  нет ли уже кнопки gruz_weight

                                us_container.addView(this.us_button_gruz_weight)  // add weight button for gruz_auto
                                setButtonWidth(this.us_button_gruz_weight)
                                setStateButtonUsGruzCarWeight(us_gruz_car_weight)

                            }

                        }
                        "bus" -> {
                            us_container.removeView(radioGroup_us_legk_gibrid_motor)   // delete legkcar gibridRadiogroup
                            us_container.removeView(us_button_gruz_weight) // delete gruzcar weight button

                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "bus")
                            us_switchButton_legk_gibrid.visibility = GONE
                            set_us_button_kind_of_auto(
                                R.string.title_button_bus,
                                R.drawable.ic_directions_bus_black_24dp
                            )

                        }
                        "dumpTruck" -> {
                            us_container.removeView(us_button_gruz_weight)  // delete gruzcar weight button
                            us_container.removeView(radioGroup_us_legk_gibrid_motor)   // delete legkcar gibridRadiogroup

                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "dumpTruck")
                            us_switchButton_legk_gibrid.visibility = GONE
                            set_us_button_kind_of_auto(
                                R.string.title_button_dumpTruck,
                                R.drawable.ic_gruz_black_24dp
                            )

                        }
                        "pricep" -> {
                            us_container.removeView(us_button_gruz_weight)     // delete gruzcar weight button
                            us_container.removeView(radioGroup_us_legk_gibrid_motor)   // delete legkcar gibridRadiogroup

                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_KINDAUTO, "pricep")
                            us_switchButton_legk_gibrid.visibility = GONE
                            set_us_button_kind_of_auto(
                                R.string.title_button_pricep,
                                R.drawable.ic_pricep
                            )
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
                            set_us_button_gruz_weight(
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
                            set_us_button_gruz_weight(
                                R.string.title_button_in_2_5_3_5t_us,
                                R.drawable.ic_hexagon_slice_1
                            )
                        }
                        "3_5_5t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "3_5_5t")
                            set_us_button_gruz_weight(
                                R.string.title_button_in_3_5_5t_us,
                                R.drawable.ic_hexagon_slice_2
                            )
                        }
                        "5_8t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "5_8t")
                            set_us_button_gruz_weight(
                                R.string.title_button_in_5_8t_us,
                                R.drawable.ic_hexagon_slice_3
                            )
                        }
                        "8_12t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "8_12t")
                            set_us_button_gruz_weight(
                                R.string.title_button_in_8_12t_us,
                                R.drawable.ic_hexagon_slice_4
                            )
                        }
                        "12_20t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "12_20t")
                            set_us_button_gruz_weight(
                                R.string.title_button_in_12_20t_us,
                                R.drawable.ic_hexagon_slice_5
                            )
                        }
                        "20_50t" -> {
                            model.putSprefs(sprefUtilSbor, SPREF_UTILSBOR_GRUZ_CAR_WEIGHT, "20_50t")
                            set_us_button_gruz_weight(
                                R.string.title_button_in_20_50t_us,
                                R.drawable.ic_hexagon_slice_6
                            )
                        }
                    }
                } // end REQUEST_CODE_GRUZ_AUTO_WEIGHT =============================================


            }

        }
    }

    private fun set_us_button_kind_of_auto(titleButtonLegkCar: Int, iconButtonLegkCar: Int) {
        us_button_kind_of_auto.text = resources.getString(titleButtonLegkCar)
        us_button_kind_of_auto.setIconResource(iconButtonLegkCar)

    }

    private fun set_us_button_gruz_weight(titleButtonGruzWeight: Int, iconButtonGruzWeight: Int) {

        us_button_gruz_weight.text = resources.getString(titleButtonGruzWeight)
        us_button_gruz_weight.gravity = TEXT_ALIGNMENT_VIEW_END
        us_button_gruz_weight.setIconResource(iconButtonGruzWeight)
    }


    fun setStartSettingbuttons() {
        when (this.us_kindAuto) {
            "legk_car" -> {
                set_us_button_kind_of_auto(
                    R.string.title_button_legk_car, R.drawable.ic_car_black_24dp
                )
                if (us_legk_car_gibrid_switch) {
                    us_switchButton_legk_gibrid.isChecked = true
                    us_container.addView(radioGroup_us_legk_gibrid_motor)   // if swith gibrid is on than add radiogroup

                    when (us_legk_car_gibrid_capacity) {
                        "less_1000" -> radioGroup_us_legk_gibrid_motor.check(R.id.less_1000)
                        "in_1000_2000" -> radioGroup_us_legk_gibrid_motor.check(R.id.in_1000_2000)
                        "in_2000_3000" -> radioGroup_us_legk_gibrid_motor.check(R.id.in_2000_3000)
                        "in_3000_3500" -> radioGroup_us_legk_gibrid_motor.check(R.id.in_3000_3500)
                        "more_3500" -> radioGroup_us_legk_gibrid_motor.check(R.id.more_3500)
                    }
                }
            }
            "gruz_car" -> {
                us_switchButton_legk_gibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_gruz_car_us,
                    R.drawable.ic_gruz_black_24dp
                )
                us_container.addView(us_button_gruz_weight)  // add weight button for gruz_auto
                setButtonWidth(us_button_gruz_weight)
                setStateButtonUsGruzCarWeight(us_gruz_car_weight)



            }
            "bus" -> {
                us_switchButton_legk_gibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_bus,
                    R.drawable.ic_directions_bus_black_24dp
                )
            }
            "dumpTruck" -> {
                us_switchButton_legk_gibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_dumpTruck,
                    R.drawable.ic_gruz_black_24dp
                )

            }
            "pricep" -> {
                us_switchButton_legk_gibrid.visibility = GONE
                set_us_button_kind_of_auto(
                    R.string.title_button_pricep,
                    R.drawable.ic_pricep
                )
            }

        }
    }

    private fun setStateButtonUsGruzCarWeight(usGruzCarWeight: String) {

        when (usGruzCarWeight) {
            "less_2_5t" -> {
                set_us_button_gruz_weight(
                    R.string.title_button_less_2_5t_us,
                    R.drawable.ic_hexagon_outline
                )
            }
            "2_5_3_5t" -> {
                set_us_button_gruz_weight(
                    R.string.title_button_in_2_5_3_5t_us,
                    R.drawable.ic_hexagon_slice_1
                )
            }
            "3_5_5t" -> {
                set_us_button_gruz_weight(
                    R.string.title_button_in_3_5_5t_us,
                    R.drawable.ic_hexagon_slice_2
                )
            }
            "5_8t" -> {
                set_us_button_gruz_weight(
                    R.string.title_button_in_5_8t_us,
                    R.drawable.ic_hexagon_slice_3
                )
            }
            "8_12t" -> {
                set_us_button_gruz_weight(
                    R.string.title_button_in_8_12t_us,
                    R.drawable.ic_hexagon_slice_4
                )
            }
            "12_20t" -> {
                set_us_button_gruz_weight(
                    R.string.title_button_in_12_20t_us,
                    R.drawable.ic_hexagon_slice_5
                )
            }
            "20_50t" -> {
                set_us_button_gruz_weight(
                    R.string.title_button_in_20_50t_us,
                    R.drawable.ic_hexagon_slice_6
                )
            }
        }
    }
}