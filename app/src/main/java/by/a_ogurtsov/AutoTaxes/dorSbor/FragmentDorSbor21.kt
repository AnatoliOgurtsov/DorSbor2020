package by.a_ogurtsov.AutoTaxes.dorSbor

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.a_ogurtsov.AutoTaxes.*
import by.a_ogurtsov.AutoTaxes.viewModels.MyViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.switchmaterial.SwitchMaterial


class FragmentDorSbor21 : Fragment() {

    private val LAYOUT: Int = R.layout.fragment_dor_sbor

    private val color: String = "привет"
    private val widthScreenDPI: String = ""

    private lateinit var buttonToggleGroup_fiz_yur: MaterialButtonToggleGroup
    private lateinit var buttonFiz: MaterialButton
    private lateinit var buttonYur: MaterialButton
    private lateinit var buttonKindAuto: MaterialButton
    private lateinit var buttonKindAutoWeight: MaterialButton
    private lateinit var switchButtonPens: SwitchMaterial
    private lateinit var switchButtonInvalid3Group: SwitchMaterial
    private lateinit var coordinatorLayout: CoordinatorLayout


    private var fizYur: String = "fiz"
    private var kindAuto: String = "legk_car"
    private var weightAuto: String = "less_1_5t"
    private var veteran: Int =
        1                    // 1 - неветеран, 2 - ветеран (50 проц. скидка), 3 - инвалид 3группы (25 проц. скидка)

    private val SPREF_DORSBOR_NAME = "sprefDorsbor"
    private val SPREF_DORSBOR_FIZYUR = "fizYur"
    private val SPREF_DORSBOR_KINDAUTO = "kindAuto"
    private val SPREF_DORSBOR_WEIGHTAUTO = "weightAuto"
    private val SPREF_DORSBOR_VETERAN = "veteran"

    private lateinit var textView_Sums_Value: TextView

    private lateinit var model: MyViewModel
    private lateinit var sprefDorSbor: SharedPreferences

    val REQUEST_CODE_KIND_OF_AUTO = 1
    val REQUEST_CODE_KIND_OF_AUTO_WEIGHT = 2

    var resultOfKIND_OF_AUTO: String = "legk_car"

    fun newInstance(param1: String, param2: Int): FragmentDorSbor21 {
        return FragmentDorSbor21().apply {
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

        model = ViewModelProviders.of(this).get(MyViewModel::class.java)

        textView_Sums_Value = view.findViewById(R.id.textView_Sums_Value)

        initSpref()

        buttonFiz = view.findViewById(R.id.button_fiz)
        buttonYur = view.findViewById(R.id.button_yur)
        buttonKindAuto = view.findViewById(R.id.button_kind_of_auto)
        buttonKindAutoWeight = view.findViewById(R.id.button_kind_of_auto_weight)
        switchButtonPens = view.findViewById(R.id.switch_button_pensioner)
        switchButtonInvalid3Group = view.findViewById(R.id.switch_button_invaliv3group)
        coordinatorLayout = view.findViewById(R.id.coordinatorLayout)
        buttonToggleGroup_fiz_yur = view.findViewById(R.id.buttonToggleGroup_fiz_yur)

        setbuttonToggleGroup(
            view,
            R.id.buttonToggleGroup_fiz_yur,
            buttonFiz.id,
            buttonYur.id
        )
        setButtonWidth(buttonKindAuto)
        setButtonWidth(buttonKindAutoWeight)

        //========начальные состояния buttons=========================
        //==========================================================================================
        setStartSettingbuttons()
        //==========================================================================================


        val onButtonClickListener: View.OnClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.button_fiz -> {
                    buttonFiz.isChecked =
                        true         // если при нажатии на кнопку она уже checked, то toggleGroup снимает checked, поэтому снова устанавливаем Checked
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_FIZYUR, "fiz")
                    if (switchButtonPens.visibility == GONE)
                        switchButtonPens.visibility = VISIBLE
                    if (switchButtonInvalid3Group.visibility == GONE)
                        switchButtonInvalid3Group.visibility = VISIBLE

                    when (kindAuto) {
                        "legk_car" -> {
                            buttonKindAutoWeight.text =
                                resources.getString(R.string.title_button_less_1_5t21)
                            buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            model.putSprefs(
                                sprefDorSbor,
                                SPREF_DORSBOR_WEIGHTAUTO,
                                "less_1_5t"
                            )
                        }
                    }
                }
                R.id.button_yur -> {
                    buttonYur.isChecked = true
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_FIZYUR, "yur")
                    /*model.putSprefs(
                        sprefDorSbor,
                        SPREF_DORSBOR_VETERAN,
                        1
                    )     */
                    switchButtonPens.visibility = GONE
                    switchButtonInvalid3Group.visibility =
                        GONE// при нажатии на юр лицо ветеран не нужен

                    when (kindAuto) {
                        "legk_car" -> {
                            buttonKindAutoWeight.text =
                                resources.getString(R.string.title_button_less_1t21)
                            buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            model.putSprefs(
                                sprefDorSbor,
                                SPREF_DORSBOR_WEIGHTAUTO,
                                "less_1t_yur"
                            )
                        }
                    }

                }

                R.id.button_kind_of_auto -> {
                    val intent = Intent(activity, ActivityKindOfAuto::class.java)
                    startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO)

                }
                R.id.button_kind_of_auto_weight -> {

                    when (resultOfKIND_OF_AUTO) {

                        "legk_car" -> {

                            when (fizYur) {
                                "fiz" -> {
                                    val intent =
                                        Intent(activity, ActivityWeightLegkAutoFiz::class.java)
                                    startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO_WEIGHT)
                                }
                                "yur" -> {
                                    val intent =
                                        Intent(activity, ActivityWeightLegkAutoYur::class.java)
                                    startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO_WEIGHT)
                                }
                            }
                        }
                        "gruz_car" -> {
                            val intent = Intent(activity, ActivityWeightGruzAuto::class.java)
                            startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO_WEIGHT)
                        }
                        "bus" -> {
                            val intent = Intent(activity, ActivityWeightBus::class.java)
                            startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO_WEIGHT)
                        }
                        "pricep" -> {
                            val intent = Intent(activity, ActivityWeightPricep::class.java)
                            startActivityForResult(intent, REQUEST_CODE_KIND_OF_AUTO_WEIGHT)
                        }
                        "motocycle" -> {

                        }
                    }
                }
            }
        }
        buttonFiz.setOnClickListener(onButtonClickListener)
        buttonYur.setOnClickListener(onButtonClickListener)
        buttonKindAuto.setOnClickListener(onButtonClickListener)
        buttonKindAutoWeight.setOnClickListener(onButtonClickListener)

        // начальное состояние switch ветеран\неветеран
        when (veteran) {
            2 -> {
                switchButtonPens.isChecked = true
                switchButtonInvalid3Group.isChecked = false
            }
            1 -> {
                switchButtonPens.isChecked = false
                switchButtonInvalid3Group.isChecked = false
            }
            3 -> {
                switchButtonPens.isChecked = false
                switchButtonInvalid3Group.isChecked = true
            }

        }
        //============================================


        switchButtonPens.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                veteran = 2
                switchButtonInvalid3Group.isChecked = !isChecked
                model.putSprefs(sprefDorSbor, SPREF_DORSBOR_VETERAN, veteran)
            } else {
                if (veteran == 2) {
                    veteran = 1
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_VETERAN, veteran)
                }
            }
        }
        switchButtonInvalid3Group.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                veteran = 3
                switchButtonPens.isChecked = !isChecked
                model.putSprefs(sprefDorSbor, SPREF_DORSBOR_VETERAN, veteran)
            } else {
                if (veteran == 3) {
                    veteran = 1
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_VETERAN, veteran)
                }
            }

        }
        return view
    }

    fun setbuttonToggleGroup(view: View, idButtonToogleGroup: Int, idButton1: Int, idButton2: Int) {

        val buttonToogleGroup: MaterialButtonToggleGroup = view.findViewById(idButtonToogleGroup)

        val button1: MaterialButton = view.findViewById(idButton1)
        button1.layoutParams.width =
            requireArguments().getInt(widthScreenDPI) / 2 - 40   // устанавливаем ширину кнопки по размеру экрана

        val button2: MaterialButton = view.findViewById(idButton2)
        button2.layoutParams.width =
            requireArguments().getInt(widthScreenDPI) / 2 - 40      // устанавливаем ширину кнопки по размеру экрана

        // устанавливаем начальное состояние кнопок физюр и возраст
        if (idButtonToogleGroup == R.id.buttonToggleGroup_fiz_yur) {
            when (fizYur) {
                "fiz" -> buttonToogleGroup.check(idButton1)
                "yur" -> buttonToogleGroup.check(idButton2)
            }
        }
    }

    fun setButtonWidth(button: MaterialButton) {
        button.layoutParams.width =
            requireArguments().getInt(widthScreenDPI) - 80   // устанавливаем ширину кнопки без группы
    }

    fun initSpref() {
        sprefDorSbor =
            requireContext().getSharedPreferences(SPREF_DORSBOR_NAME, Context.MODE_PRIVATE)
        if (sprefDorSbor.contains(SPREF_DORSBOR_FIZYUR)) {
            this.fizYur = sprefDorSbor.getString(SPREF_DORSBOR_FIZYUR, "").toString()
            this.kindAuto = sprefDorSbor.getString(SPREF_DORSBOR_KINDAUTO, "").toString()
            this.weightAuto = sprefDorSbor.getString(SPREF_DORSBOR_WEIGHTAUTO, "").toString()
            this.veteran = sprefDorSbor.getInt(SPREF_DORSBOR_VETERAN, 1)

        } else {
            val editor: SharedPreferences.Editor = sprefDorSbor.edit()
            editor.putString(SPREF_DORSBOR_FIZYUR, fizYur)
            editor.putString(SPREF_DORSBOR_KINDAUTO, kindAuto)
            editor.putString(SPREF_DORSBOR_WEIGHTAUTO, weightAuto)
            editor.putInt(SPREF_DORSBOR_VETERAN, veteran)
            editor.apply()
        }

        initPreferencesObserver()
    }

    fun initPreferencesObserver() {

        sprefDorSbor.stringLiveData(SPREF_DORSBOR_FIZYUR, "")
            .observe(viewLifecycleOwner, Observer<String> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_FIZYUR")
                this.fizYur = value
                textView_Sums_Value.text =
                    model.calculateSumsValue21(fizYur, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.stringLiveData(SPREF_DORSBOR_KINDAUTO, "")
            .observe(viewLifecycleOwner, Observer<String> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_KINDAUTO")
                this.kindAuto = value
                textView_Sums_Value.text =
                    model.calculateSumsValue21(fizYur, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.stringLiveData(SPREF_DORSBOR_WEIGHTAUTO, "")
            .observe(viewLifecycleOwner, Observer<String> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_WEIGHTAUTO")
                this.weightAuto = value
                textView_Sums_Value.text =
                    model.calculateSumsValue21(fizYur, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.intLiveData(SPREF_DORSBOR_VETERAN, 1)
            .observe(viewLifecycleOwner, Observer<Int> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_VETERAN")
                this.veteran = value
                textView_Sums_Value.text =
                    model.calculateSumsValue21(fizYur, kindAuto, weightAuto, veteran)
            }
            )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                // return from activity KIND_OF_AUTO================================================
                REQUEST_CODE_KIND_OF_AUTO -> {
                    when (data!!.getStringExtra("kind_of_auto")) {
                        "legk_car" -> {
                            buttonKindAuto.text =
                                resources.getString(R.string.title_button_legk_car)
                            buttonKindAuto.setIconResource(R.drawable.ic_car_black_24dp)

                            when (fizYur) {
                                "fiz" -> {
                                    buttonKindAutoWeight.text =
                                        resources.getString(R.string.title_button_less_1_5t21)
                                    buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                                    model.putSprefs(
                                        sprefDorSbor,
                                        SPREF_DORSBOR_WEIGHTAUTO,
                                        "less_1_5t"
                                    )
                                }
                                "yur" -> {
                                    buttonKindAutoWeight.text =
                                        resources.getString(R.string.title_button_less_1t21)
                                    buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                                    model.putSprefs(
                                        sprefDorSbor,
                                        SPREF_DORSBOR_WEIGHTAUTO,
                                        "less_1t_yur"
                                    )
                                }
                            }

                            resultOfKIND_OF_AUTO = "legk_car"

                            if (buttonKindAutoWeight.visibility == GONE)// восстанавливаем видимость кнопки после мотоцикла*/
                                buttonKindAutoWeight.visibility = VISIBLE
                            if (buttonToggleGroup_fiz_yur.visibility == GONE) // восстанавливаем видимость кнопок физюр после автобусов
                                buttonToggleGroup_fiz_yur.visibility = VISIBLE

                        }
                        "gruz_car" -> {
                            buttonKindAuto.text =
                                resources.getString(R.string.title_button_gruz_car)
                            buttonKindAuto.setIconResource(R.drawable.ic_gruz_black_24dp)
                            buttonKindAutoWeight.text =
                                resources.getString(R.string.title_button_less_2_5t)
                            buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            model.putSprefs(sprefDorSbor, SPREF_DORSBOR_WEIGHTAUTO, "less_2_5t")
                            resultOfKIND_OF_AUTO = "gruz_car"

                            if (buttonKindAutoWeight.visibility == GONE)// восстанавливаем видимость кнопки после мотоцикла*/
                                buttonKindAutoWeight.visibility = VISIBLE
                            if (buttonToggleGroup_fiz_yur.visibility == GONE) // восстанавливаем видимость кнопок физюр после автобусов
                                buttonToggleGroup_fiz_yur.visibility = VISIBLE

                        }
                        "bus" -> {
                            buttonKindAuto.text = resources.getString(R.string.title_button_bus)
                            buttonKindAuto.setIconResource(R.drawable.ic_directions_bus_black_24dp)
                            buttonKindAutoWeight.text =
                                resources.getString(R.string.title_button_less20)
                            buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            model.putSprefs(sprefDorSbor, SPREF_DORSBOR_WEIGHTAUTO, "less_20")
                            resultOfKIND_OF_AUTO = "bus"

                            /* buttonToggleGroup_fiz_yur.visibility =
                                 GONE // отключаем кнопки физюр для автобусов т.к. они не учитываются*/

                            if (buttonKindAutoWeight.visibility == GONE)// восстанавливаем видимость кнопки после мотоцикла*/
                                buttonKindAutoWeight.visibility = VISIBLE

                        }
                        "pricep" -> {
                            buttonKindAuto.text = resources.getString(R.string.title_button_pricep)
                            buttonKindAuto.setIconResource(R.drawable.ic_pricep)
                            buttonKindAutoWeight.text =
                                resources.getString(R.string.title_button_less_0_75)
                            buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            model.putSprefs(sprefDorSbor, SPREF_DORSBOR_WEIGHTAUTO, "less_0_75")
                            resultOfKIND_OF_AUTO = "pricep"

                            if (buttonKindAutoWeight.visibility == GONE)// восстанавливаем видимость кнопки после мотоцикла*/
                                buttonKindAutoWeight.visibility = VISIBLE
                            if (buttonToggleGroup_fiz_yur.visibility == GONE) // восстанавливаем видимость кнопок физюр после автобусов
                                buttonToggleGroup_fiz_yur.visibility = VISIBLE

                        }
                        "motocycle" -> {
                            buttonKindAuto.text =
                                resources.getString(R.string.title_button_motocycle)
                            buttonKindAuto.setIconResource(R.drawable.ic_motorcycle_black_24dp)
                            resultOfKIND_OF_AUTO = "motocycle"

                            buttonKindAutoWeight.visibility =
                                GONE   // удаляем с экрана кнопку весса ТС
                            if (buttonToggleGroup_fiz_yur.visibility == GONE) // восстанавливаем видимость кнопок физюр после автобусов
                                buttonToggleGroup_fiz_yur.visibility = VISIBLE

                            model.putSprefs(
                                sprefDorSbor,
                                SPREF_DORSBOR_WEIGHTAUTO,
                                "motocycle_nodata"
                            )
                        }
                    }
                    model.putSprefs(
                        sprefDorSbor,
                        SPREF_DORSBOR_KINDAUTO,
                        data.getStringExtra("kind_of_auto")
                    )
                    //   Log.d("myLogs", "${data!!.getStringExtra("kind_of_auto")}")
                }

                // return from activity KIND_OF_AUTO_WEIGHT=========================================
                //==================================================================================
                //==================================================================================
                REQUEST_CODE_KIND_OF_AUTO_WEIGHT -> {

                    if (data!!.hasExtra("weight_of_legk_auto")) {
                        when (data.getStringExtra("weight_of_legk_auto")) {
                            //fiz
                            "less_1_5t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_less_1_5t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_outline)
                            }
                            "1_5_1_75t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_1_5_1_75t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            }
                            "1_75_2t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_1_75_2t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_2)
                            }
                            "2_2_25t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_2_2_25t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_3)
                            }
                            "2_25_2_5t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_1_25_2_5t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_4)
                            }
                            "2_5_3t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_2_5_3t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_5)
                            }
                            "more_3t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_more_3t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
                            }
                            "legk_electro" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_electro)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_outline)
                            }
                            // yur
                            "less_1t_yur" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_less_1t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            }
                            "1_2t_yur" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_1_2t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_2)
                            }
                            "2_3t_yur" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_2_3t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_4)
                            }
                            "more_3t_yur" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_more_3t21)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
                            }
                        }
                        model.putSprefs(
                            sprefDorSbor,
                            SPREF_DORSBOR_WEIGHTAUTO,
                            data.getStringExtra("weight_of_legk_auto")
                        )
                    }
                    //==============================================================================
                    //=========================gruz auto============================================
                    else if (data.hasExtra("weight_of_gruz_auto")) {

                        when (data.getStringExtra("weight_of_gruz_auto")) {
                            "less_2_5t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_less_2_5t)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                                /*    if (buttonToggleGroup_fiz_yur.visibility == GONE)
                                    buttonToggleGroup_fiz_yur.visibility = VISIBLE*/
                            }
                            "2_5_3_5t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_2_5_3_5t)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_2)
                                /*   buttonToggleGroup_fiz_yur.visibility = GONE*/
                            }
                            "3_5_12t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_3_5_12t)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_4)
                                /*  buttonToggleGroup_fiz_yur.visibility = GONE*/
                            }
                            "more_12t" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_more_12t)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
                                /*   buttonToggleGroup_fiz_yur.visibility = GONE*/
                            }
                            "semi_trailer" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_semi_trailer)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_semi_trailer)
                                /*     buttonToggleGroup_fiz_yur.visibility = GONE*/
                            }
                        }
                        model.putSprefs(
                            sprefDorSbor,
                            SPREF_DORSBOR_WEIGHTAUTO,
                            data.getStringExtra("weight_of_gruz_auto")
                        )
                    }
                    //==============================================================================
                    //===========================bus================================================
                    else if (data.hasExtra("weight_of_bus")) {

                        when (data.getStringExtra("weight_of_bus")) {
                            "less_20" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_less20)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            }
                            "20_40" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_in_21_40)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_3)
                            }
                            "more_40" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_more_40)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
                            }
                        }
                        model.putSprefs(
                            sprefDorSbor,
                            SPREF_DORSBOR_WEIGHTAUTO,
                            data.getStringExtra("weight_of_bus")
                        )
                    }
                    //==============================================================================
                    //============================pricep+===========================================
                    else if (data.hasExtra("weight_of_pricep")) {

                        when (data.getStringExtra("weight_of_pricep")) {
                            "less_0_75" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_less_0_75)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
                            }
                            "more_0_75" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_more_0_75)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_3)
                            }
                            "dacha" -> {
                                buttonKindAutoWeight.text =
                                    resources.getString(R.string.title_button_dacha)
                                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
                            }
                        }
                        model.putSprefs(
                            sprefDorSbor,
                            SPREF_DORSBOR_WEIGHTAUTO,
                            data.getStringExtra("weight_of_pricep")
                        )
                    }

                }

            }

        } else Log.d("myLogs", "Something wrong")
    }

    fun setStartSettingbuttons() {
        resultOfKIND_OF_AUTO = kindAuto
        when (this.kindAuto) {
            "legk_car" -> {
                buttonKindAuto.text = resources.getString(R.string.title_button_legk_car)
                buttonKindAuto.setIconResource(R.drawable.ic_car_black_24dp)
            }
            "gruz_car" -> {
                buttonKindAuto.text = resources.getString(R.string.title_button_gruz_car)
                buttonKindAuto.setIconResource(R.drawable.ic_gruz_black_24dp)

            }
            "bus" -> {
                buttonKindAuto.text = resources.getString(R.string.title_button_bus)
                buttonKindAuto.setIconResource(R.drawable.ic_directions_bus_black_24dp)
                buttonToggleGroup_fiz_yur.visibility = GONE
            }
            "pricep" -> {
                buttonKindAuto.text = resources.getString(R.string.title_button_pricep)
                buttonKindAuto.setIconResource(R.drawable.ic_pricep)
            }
            "motocycle" -> {
                buttonKindAuto.text =
                    resources.getString(R.string.title_button_motocycle)
                buttonKindAuto.setIconResource(R.drawable.ic_motorcycle_black_24dp)
                buttonKindAutoWeight.visibility =
                    GONE   // удаляем с экрана кнопку весса ТС
            }
        }
        when (this.weightAuto) {

            //legk auto
            "less_1_5t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_less_1_5t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_outline)
            }
            "1_5_1_75t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_1_5_1_75t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
            }
            "1_75_2t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_1_75_2t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_2)
            }
            "2_2_25t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_2_2_25t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_3)
            }
            "2_25_2_5t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_1_25_2_5t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_4)
            }
            "2_5_3t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_2_5_3t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_5)
            }
            "more_3t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_more_3t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
            }
            "legk_electro" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_electro)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_outline)
            }
            // yur
            "less_1t_yur" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_less_1t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
            }
            "1_2t_yur" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_1_2t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_2)
            }
            "2_3t_yur" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_2_3t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_4)
            }
            "more_3t_yur" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_more_3t21)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
            }


            //gruz auto
            "less_2_5t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_less_2_5t)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
            }
            "2_5_3_5t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_2_5_3_5t)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_2)
            }
            "3_5_12t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_3_5_12t)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_4)
            }
            "more_12t" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_more_12t)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
            }
            "semi_trailer" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_semi_trailer)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_semi_trailer)
            }

            //bus
            "less_20" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_less20)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
            }
            "20_40" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_in_21_40)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_3)
            }
            "more_40" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_more_40)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
            }

            //pricep
            "less_0_75" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_less_0_75)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_1)
            }
            "more_0_75" -> {
                buttonKindAutoWeight.text =
                    resources.getString(R.string.title_button_more_0_75)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_3)
            }
            "dacha" -> {
                buttonKindAutoWeight.text = resources.getString(R.string.title_button_dacha)
                buttonKindAutoWeight.setIconResource(R.drawable.ic_hexagon_slice_6)
            }

        }
        when (this.fizYur) {
            "fiz" -> {
                switchButtonPens.visibility = VISIBLE
                switchButtonInvalid3Group.visibility = VISIBLE
            }
            "yur" -> {
                switchButtonPens.visibility = GONE
                switchButtonInvalid3Group.visibility = GONE
            }
        }
    }

}