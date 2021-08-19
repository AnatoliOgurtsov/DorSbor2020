package by.a_ogurtsov.AutoTaxes.utilSbor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton


class ActivityWeightGruzAutoUs : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_us_weight_of_gruz_auto

    private lateinit var buttonLess25tUs: MaterialButton
    private lateinit var buttonIn2535tUs: MaterialButton
    private lateinit var buttonIn355t: MaterialButton
    private lateinit var buttonIn58t: MaterialButton
    private lateinit var buttonIn812t: MaterialButton
    private lateinit var buttonIn1220t: MaterialButton
    private lateinit var buttonIn2030t: MaterialButton
    private lateinit var buttonIn3050t: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        buttonLess25tUs = findViewById(R.id.button_less_2_5t_us)
        buttonIn2535tUs = findViewById(R.id.button_in_2_5_3_5t_us)
        buttonIn355t = findViewById(R.id.button_in_3_5_5t)
        buttonIn58t = findViewById(R.id.button_in_5_8t)
        buttonIn812t = findViewById(R.id.button_in_8_12t)
        buttonIn1220t = findViewById(R.id.button_in_12_20t)
        buttonIn2030t = findViewById(R.id.button_in_20_30t)
        buttonIn3050t = findViewById(R.id.button_in_30_50t)


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
                R.id.button_in_20_30t -> {
                    intent.putExtra("weight_of_gruz_auto_us", "20_30t")
                }
                R.id.button_in_30_50t -> {
                    intent.putExtra("weight_of_gruz_auto_us", "30_50t")
                }
            }
            setResult(Activity.RESULT_OK, intent)  // pass weight of gruz auto to FragmentUtilSbor
            finish()
        }
        buttonLess25tUs.setOnClickListener(onButtonClickListener)
        buttonIn2535tUs.setOnClickListener(onButtonClickListener)
        buttonIn355t.setOnClickListener(onButtonClickListener)
        buttonIn58t.setOnClickListener(onButtonClickListener)
        buttonIn812t.setOnClickListener(onButtonClickListener)
        buttonIn1220t.setOnClickListener(onButtonClickListener)
        buttonIn2030t.setOnClickListener(onButtonClickListener)
        buttonIn3050t.setOnClickListener(onButtonClickListener)
    }
}