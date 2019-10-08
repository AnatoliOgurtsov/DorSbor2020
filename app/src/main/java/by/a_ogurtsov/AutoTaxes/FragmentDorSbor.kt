package by.a_ogurtsov.AutoTaxes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.a_ogurtsov.AutoTaxes.viewModels.MyViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup


class FragmentDorSbor : Fragment() {

    private val LAYOUT: Int = R.layout.fragment_dor_sbor

    private val color: String = "привет"
    private val widthScreenDPI: String = ""

    private lateinit var buttonToggleGroup_fiz_yur: MaterialButtonToggleGroup
    private lateinit var buttonFiz: MaterialButton
    private lateinit var buttonYur: MaterialButton
    private lateinit var buttonBefore10Years: MaterialButton
    private lateinit var button10YearsAndOldest: MaterialButton
    private lateinit var buttonKindAuto: MaterialButton
    private lateinit var buttonKindAutoWeight: MaterialButton

    private var period: String = "year"
    private var fizYur: String = "fiz"
    private var age: Int = 1            // 1 - старше 10 лет(на 1 год), 2 - младше 1- лет(на 2 года)
    private var kindAuto: String = "legkovoi"
    private var weightAuto: String = "???????"
    private var veteran: Int = 1                    // 1 - неветеран, 2 - ветеран (50 проц. скидка)

    val SPREF_DORSBOR_NAME = "sprefDorsbor"
    val SPREF_DORSBOR_PERIOD = "period"
    val SPREF_DORSBOR_FIZYUR = "fizYur"
    val SPREF_DORSBOR_AGE = "age"
    val SPREF_DORSBOR_KINDAUTO = "kindAuto"
    val SPREF_DORSBOR_WEIGHTAUTO = "weightAuto"
    val SPREF_DORSBOR_VETERAN = "veteran"

    private lateinit var textView_Sums_Value: TextView
    private lateinit var textView_Number_of_Base_Value: TextView

    private lateinit var model: MyViewModel
    private lateinit var sprefDorSbor: SharedPreferences


    fun newInstance(param1: String, param2: Int): FragmentDorSbor {
        val fragment = FragmentDorSbor()
        val args = Bundle()
        args.putString(color, param1)
        args.putInt(widthScreenDPI, param2)
        fragment.arguments = args
        return fragment
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(LAYOUT, container, false)

        model = ViewModelProviders.of(this).get(MyViewModel::class.java)

        textView_Sums_Value = view.findViewById(R.id.textView_Sums_Value)
        textView_Number_of_Base_Value = view.findViewById(R.id.textView_Number_of_Base_Value)

        initSpref()

        buttonFiz = view.findViewById(R.id.button_fiz)
        buttonYur = view.findViewById(R.id.button_yur)
        buttonBefore10Years = view.findViewById(R.id.button_before_10_years)
        button10YearsAndOldest = view.findViewById(R.id.button_10_years_and_oldest)
        buttonKindAuto = view.findViewById(R.id.button_kind_of_auto)
        buttonKindAutoWeight = view.findViewById(R.id.button_kind_of_auto_weight)



        setbuttonToggleGroup(
            view,
            R.id.buttonToggleGroup_fiz_yur,
            buttonFiz.id,
            buttonYur.id
        )
        setbuttonToggleGroup(
            view,
            R.id.buttonToggleGroup_vozrast,
            buttonBefore10Years.id,
            button10YearsAndOldest.id
        )
        setButtonWidth(buttonKindAuto)
        setButtonWidth(buttonKindAutoWeight)


        val onButtonClickListener: View.OnClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.button_fiz -> {
                    buttonFiz.isChecked =
                        true         // если при нажатии на кнопку она уже checked, то toggleGroup снимает checked, поэтому снова устанавливаем Checked
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_FIZYUR, "fiz")
                }
                R.id.button_yur -> {
                    buttonYur.isChecked = true
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_FIZYUR, "yur")
                }
                R.id.button_before_10_years -> {
                    buttonBefore10Years.isChecked = true
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_AGE, 2)
                }
                R.id.button_10_years_and_oldest -> {
                    button10YearsAndOldest.isChecked = true
                    model.putSprefs(sprefDorSbor, SPREF_DORSBOR_AGE, 1)

                }
                R.id.button_kind_of_auto -> {


                }
                R.id.button_kind_of_auto_weight -> {

                }

            }
        }
        buttonFiz.setOnClickListener(onButtonClickListener)
        buttonYur.setOnClickListener(onButtonClickListener)
        buttonBefore10Years.setOnClickListener(onButtonClickListener)
        button10YearsAndOldest.setOnClickListener(onButtonClickListener)

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

        // устанавливаем начальное состояние кнопок физюр и возраст
        if (idButtonToogleGroup == R.id.buttonToggleGroup_fiz_yur) {
            when (fizYur) {
                "fiz" -> buttonToogleGroup.check(idButton1)
                "yur" -> buttonToogleGroup.check(idButton2)
            }
        } else if (idButtonToogleGroup == R.id.buttonToggleGroup_vozrast) {
            when (age) {
                2 -> buttonToogleGroup.check(idButton1)
                1 -> buttonToogleGroup.check(idButton2)
            }
        } //========================================================

    }

    fun setButtonWidth(button: MaterialButton) {
        button.layoutParams.width =
            arguments!!.getInt(widthScreenDPI) - 80   // устанавливаем ширину кнопки без группы
    }

    fun initSpref() {
        sprefDorSbor = context!!.getSharedPreferences(SPREF_DORSBOR_NAME, Context.MODE_PRIVATE)
        if (sprefDorSbor!!.contains(SPREF_DORSBOR_FIZYUR)) {
            this.period = sprefDorSbor.getString(SPREF_DORSBOR_PERIOD, "")
            this.fizYur = sprefDorSbor.getString(SPREF_DORSBOR_FIZYUR, "")
            this.age = sprefDorSbor.getInt(SPREF_DORSBOR_AGE, 1)
            this.kindAuto = sprefDorSbor.getString(SPREF_DORSBOR_KINDAUTO, "")
            this.weightAuto = sprefDorSbor.getString(SPREF_DORSBOR_WEIGHTAUTO, "")
            this.veteran = sprefDorSbor.getInt(SPREF_DORSBOR_VETERAN, 1)

        }
        initPreferencesObserver()
    }

    fun initPreferencesObserver() {

        sprefDorSbor.stringLiveData(SPREF_DORSBOR_PERIOD, "")
            .observe(this, Observer<String> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_PERIOD")
                this.period = value
                textView_Sums_Value.text =
                    model.calculateSumsValue(period, fizYur, age, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.stringLiveData(SPREF_DORSBOR_FIZYUR, "")
            .observe(this, Observer<String> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_FIZYUR")
                this.fizYur = value
                textView_Sums_Value.text =
                    model.calculateSumsValue(period, fizYur, age, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.intLiveData(SPREF_DORSBOR_AGE, 1)
            .observe(this, Observer<Int> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_AGE")
                this.age = value
                textView_Sums_Value.text =
                    model.calculateSumsValue(period, fizYur, age, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.stringLiveData(SPREF_DORSBOR_KINDAUTO, "")
            .observe(this, Observer<String> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_KINDAUTO")
                this.kindAuto = value
                textView_Sums_Value.text =
                    model.calculateSumsValue(period, fizYur, age, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.stringLiveData(SPREF_DORSBOR_WEIGHTAUTO, "")
            .observe(this, Observer<String> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_WEIGHTAUTO")
                this.weightAuto = value
                textView_Sums_Value.text =
                    model.calculateSumsValue(period, fizYur, age, kindAuto, weightAuto, veteran)
            }
            )
        sprefDorSbor.intLiveData(SPREF_DORSBOR_VETERAN, 1)
            .observe(this, Observer<Int> { value ->
                Log.d("myLogs", "return from Livedata SPREF_DORSBOR_VETERAN")
                this.veteran = value
                textView_Sums_Value.text =
                    model.calculateSumsValue(period, fizYur, age, kindAuto, weightAuto, veteran)
            }
            )
    }


}
