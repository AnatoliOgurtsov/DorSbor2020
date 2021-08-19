package by.a_ogurtsov.AutoTaxes.dorSbor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton

class ActivityWeightLegkAutoYur : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_weight_of_legk_auto_yur

    private lateinit var button_less_1t: MaterialButton
    private lateinit var button_in_1_2t: MaterialButton
    private lateinit var button_in_2_3t: MaterialButton
    private lateinit var button_more_3t: MaterialButton
    private lateinit var button_electro: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_less_1t = findViewById(R.id.button_less_1t)
        button_in_1_2t = findViewById(R.id.button_in_1_2t)
        button_in_2_3t = findViewById(R.id.button_in_2_3t)
        button_more_3t = findViewById(R.id.button_more_3t)
        button_electro = findViewById(R.id.button_legk_electro_yur)


        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_less_1t -> {
                    intent.putExtra("weight_of_legk_auto", "less_1t_yur")
                }
                R.id.button_in_1_2t -> {
                    intent.putExtra("weight_of_legk_auto", "1_2t_yur")
                }
                R.id.button_in_2_3t -> {
                    intent.putExtra("weight_of_legk_auto", "2_3t_yur")
                }
                R.id.button_more_3t -> {
                    intent.putExtra("weight_of_legk_auto", "more_3t_yur")
                }
                R.id.button_legk_electro_yur -> {
                    intent.putExtra("weight_of_legk_auto", "legk_electro")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass kind of auto to FragmentDorSbor
            finish()
        }
        button_less_1t.setOnClickListener(onButtonClickListener)
        button_in_1_2t.setOnClickListener(onButtonClickListener)
        button_in_2_3t.setOnClickListener(onButtonClickListener)
        button_more_3t.setOnClickListener(onButtonClickListener)
        button_electro.setOnClickListener(onButtonClickListener)
    }
}
