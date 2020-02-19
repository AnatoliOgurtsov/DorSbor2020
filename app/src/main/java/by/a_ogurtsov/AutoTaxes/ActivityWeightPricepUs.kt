package by.a_ogurtsov.AutoTaxes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton


class ActivityWeightPricepUs : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_us_weight_of_pricep

    private lateinit var button_pricep_10t_us: MaterialButton
    private lateinit var button_halfpricep_10t_us: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()    // the function from parent activity
        setContentView(LAYOUT)

        button_pricep_10t_us = findViewById(R.id.button_pricep_10t_us)
        button_halfpricep_10t_us = findViewById(R.id.button_halfpricep_10t_us)


        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_pricep_10t_us -> {
                    intent.putExtra("weight_of_pricep_us", "more_10t_pricep")
                }
                R.id.button_halfpricep_10t_us -> {
                    intent.putExtra("weight_of_pricep_us", "more_10t_halfpricep")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass  to FragmentUtilSbor
            finish()
        }
        button_pricep_10t_us.setOnClickListener(onButtonClickListener)
        button_halfpricep_10t_us.setOnClickListener(onButtonClickListener)

    }
}