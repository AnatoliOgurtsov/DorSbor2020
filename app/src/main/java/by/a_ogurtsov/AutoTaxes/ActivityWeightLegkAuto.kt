package by.a_ogurtsov.AutoTaxes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton

class ActivityWeightLegkAuto : ActivityParent(){
    private val LAYOUT: Int = R.layout.activity_weight_of_legk_auto

    private lateinit var button_less_1_5t: MaterialButton
    private lateinit var button_in_1_5_2t: MaterialButton
    private lateinit var button_in_2_3t: MaterialButton
    private lateinit var button_more_3t: MaterialButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_less_1_5t = findViewById(R.id.button_less_1_5t)
        button_in_1_5_2t = findViewById(R.id.button_in_1_5_2t)
        button_in_2_3t = findViewById(R.id.button_in_2_3t)
        button_more_3t = findViewById(R.id.button_more_3t)


        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_less_1_5t -> {
                    intent.putExtra("weight_of_legk_auto", "less_1_5t")
                }
                R.id.button_in_1_5_2t -> {
                    intent.putExtra("weight_of_legk_auto", "1_5_2t")
                }
                R.id.button_in_2_3t -> {
                    intent.putExtra("weight_of_legk_auto", "2_3t")
                }
                R.id.button_more_3t -> {
                    intent.putExtra("weight_of_legk_auto", "more_3t")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass kind of auto to FragmentDorSbor
            finish()
        }
        button_less_1_5t.setOnClickListener(onButtonClickListener)
        button_in_1_5_2t.setOnClickListener(onButtonClickListener)
        button_in_2_3t.setOnClickListener(onButtonClickListener)
        button_more_3t.setOnClickListener(onButtonClickListener)

    }
}