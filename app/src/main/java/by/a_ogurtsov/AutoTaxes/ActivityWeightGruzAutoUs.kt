package by.a_ogurtsov.AutoTaxes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton


class ActivityWeightGruzAutoUs : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_us_weight_of_gruz_auto

    private lateinit var button_less_2_5t_us: MaterialButton
    private lateinit var button_in_2_5_3_5t_us: MaterialButton
    private lateinit var button_in_3_5_5t: MaterialButton
    private lateinit var button_in_5_8t: MaterialButton
    private lateinit var button_in_8_12t: MaterialButton
    private lateinit var button_in_12_20t: MaterialButton
    private lateinit var button_in_20_50t: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_less_2_5t_us = findViewById(R.id.button_less_2_5t_us)
        button_in_2_5_3_5t_us = findViewById(R.id.button_in_2_5_3_5t_us)
        button_in_3_5_5t = findViewById(R.id.button_in_3_5_5t)
        button_in_5_8t = findViewById(R.id.button_in_5_8t)
        button_in_8_12t = findViewById(R.id.button_in_8_12t)
        button_in_12_20t = findViewById(R.id.button_in_12_20t)
        button_in_20_50t = findViewById(R.id.button_in_20_50t)


        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_less_2_5t_us -> {
                    intent.putExtra("weight_of_gruz_auto_us", "less_2_5t")
                }
                R.id.button_in_2_5_3_5t_us -> {
                    intent.putExtra("weight_of_gruz_auto_us", "2_5_3_5t")
                }
                R.id.button_in_3_5_5t -> {
                    intent.putExtra("weight_of_gruz_auto_us", "3_5_5t")
                }
                R.id.button_in_5_8t -> {
                    intent.putExtra("weight_of_gruz_auto_us", "5_8t")
                }
                R.id.button_in_8_12t -> {
                    intent.putExtra("weight_of_gruz_auto_us", "8_12t")
                }
                R.id.button_in_12_20t -> {
                    intent.putExtra("weight_of_gruz_auto_us", "12_20t")
                }
                R.id.button_in_20_50t -> {
                    intent.putExtra("weight_of_gruz_auto_us", "20_50t")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass weight of gruz auto to FragmentUtilSbor
            finish()
        }
        button_less_2_5t_us.setOnClickListener(onButtonClickListener)
        button_in_2_5_3_5t_us.setOnClickListener(onButtonClickListener)
        button_in_3_5_5t.setOnClickListener(onButtonClickListener)
        button_in_5_8t.setOnClickListener(onButtonClickListener)
        button_in_8_12t.setOnClickListener(onButtonClickListener)
        button_in_12_20t.setOnClickListener(onButtonClickListener)
        button_in_20_50t.setOnClickListener(onButtonClickListener)

    }
}