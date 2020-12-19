package by.a_ogurtsov.AutoTaxes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton


class ActivityWeightLegkAutoUs : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_us_weight_of_legk_auto

    private lateinit var button_legk_electro_us: MaterialButton
    private lateinit var button_legk_gibrid_us: MaterialButton
    private lateinit var button_legk_fiz_us: MaterialButton
    private lateinit var button_legk_ees: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_legk_electro_us = findViewById(R.id.button_legk_electro_us)
        button_legk_gibrid_us = findViewById(R.id.button_legk_gibrid_us)
        button_legk_fiz_us = findViewById(R.id.button_legk_fiz_us)
        button_legk_ees = findViewById(R.id.button_legk_ees_us)

        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_legk_electro_us -> {
                    intent.putExtra("weight_of_legk_auto_us", "electro")
                }
                R.id.button_legk_gibrid_us -> {
                    intent.putExtra("weight_of_legk_auto_us", "gibrid")
                }
                R.id.button_legk_fiz_us -> {
                    intent.putExtra("weight_of_legk_auto_us", "fiz")
                }
                R.id.button_legk_ees_us -> {
                    intent.putExtra("weight_of_legk_auto_us", "ees")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass weight of legk auto to FragmentUtilSbor
            finish()
        }
        button_legk_electro_us.setOnClickListener(onButtonClickListener)
        button_legk_gibrid_us.setOnClickListener(onButtonClickListener)
        button_legk_fiz_us.setOnClickListener(onButtonClickListener)
        button_legk_ees.setOnClickListener(onButtonClickListener)

    }
}