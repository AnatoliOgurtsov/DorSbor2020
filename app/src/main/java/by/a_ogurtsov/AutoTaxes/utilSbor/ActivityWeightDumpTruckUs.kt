package by.a_ogurtsov.AutoTaxes.utilSbor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton


class ActivityWeightDumpTruckUs : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_us_weight_of_dumptruck

    private lateinit var button_in_50_80t_us: MaterialButton
    private lateinit var button_in_80_350t_us: MaterialButton
    private lateinit var button_more_350t_us: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()    // the function from parent activity
        setContentView(LAYOUT)

        button_in_50_80t_us = findViewById(R.id.button_in_50_80t_us)
        button_in_80_350t_us = findViewById(R.id.button_in_80_350t_us)
        button_more_350t_us = findViewById(R.id.button_more_350t_us)

        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_in_50_80t_us -> {
                    intent.putExtra("weight_of_dumpTrack_us", "50_80t")
                }
                R.id.button_in_80_350t_us -> {
                    intent.putExtra("weight_of_dumpTrack_us", "80_350t")
                }
                R.id.button_more_350t_us -> {
                    intent.putExtra("weight_of_dumpTrack_us", "more_350t")
                }


            }
            setResult(Activity.RESULT_OK, intent)  // pass weight of gruz auto to FragmentUtilSbor
            finish()
        }
        button_in_50_80t_us.setOnClickListener(onButtonClickListener)
        button_in_80_350t_us.setOnClickListener(onButtonClickListener)
        button_more_350t_us.setOnClickListener(onButtonClickListener)

    }
}