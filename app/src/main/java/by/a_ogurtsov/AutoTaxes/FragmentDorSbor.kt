package by.a_ogurtsov.AutoTaxes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup


class FragmentDorSbor : Fragment() {

    private val LAYOUT: Int = R.layout.fragment_dor_sbor

    val LOG_TAG = "myLogs"

    private val color: String = "привет"
    private val widthScreenDPI: String = ""

    private lateinit var buttonToggleGroup_fiz_yur: MaterialButtonToggleGroup
    private lateinit var buttonFiz: MaterialButton
    private lateinit var buttonYur: MaterialButton
    private lateinit var buttonBefore10Years: MaterialButton
    private lateinit var button10YearsAndOldest: MaterialButton
    private lateinit var buttonKindAuto: MaterialButton
    private lateinit var buttonKindAutoWeight: MaterialButton


    fun newInstance(param1: String, param2: Int): FragmentDorSbor {
        val fragment = FragmentDorSbor()
        val args = Bundle()
        args.putString(color, param1)
        args.putInt(widthScreenDPI, param2)
        fragment.arguments = args
        return fragment
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(LAYOUT, container, false)

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


        /*   buttonToggleGroup_fiz_yur = view.findViewById(R.id.buttonToggleGroup_fiz_yur)
           buttonToggleGroup_fiz_yur.addOnButtonCheckedListener{buttonToggleGroup_fiz_yur, checkitID, isCheckit ->
               Log.d(LOG_TAG, checkitID.toString() + isCheckit)

           }*/

        val onButtonClickListener: View.OnClickListener = View.OnClickListener() { v ->
            when (v.id) {
                R.id.button_fiz -> {
                    buttonFiz.isChecked =
                        true         // если при нажатии на кнопку она уже checked, то toggleGroup снимает checked, поэтому снова устанавливаем Checked
                }
                R.id.button_yur -> {
                    buttonYur.isChecked = true
                }
                R.id.button_before_10_years -> {
                    buttonBefore10Years.isChecked = true
                }
                R.id.button_10_years_and_oldest -> {
                    button10YearsAndOldest.isChecked = true
                }
                R.id.button_kind_of_auto ->{

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

        buttonToogleGroup.check(idButton1)
    }
    fun setButtonWidth(button: MaterialButton){
        button.layoutParams.width = arguments!!.getInt(widthScreenDPI) - 80   // устанавливаем ширину кнопки без группы
    }
}
