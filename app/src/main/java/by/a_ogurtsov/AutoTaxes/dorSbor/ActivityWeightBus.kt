package by.a_ogurtsov.AutoTaxes.dorSbor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton


class ActivityWeightBus : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_weight_of_bus

    private lateinit var button_less20: MaterialButton
    private lateinit var button_in_21_40: MaterialButton
    private lateinit var button_more_40: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_less20 = findViewById(R.id.button_less20)
        button_in_21_40 = findViewById(R.id.button_in_21_40)
        button_more_40 = findViewById(R.id.button_more_40)

        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_less20 -> {
                    intent.putExtra("weight_of_bus", "less_20")
                }
                R.id.button_in_21_40 -> {
                    intent.putExtra("weight_of_bus", "20_40")
                }
                R.id.button_more_40 -> {
                    intent.putExtra("weight_of_bus", "more_40")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass kind of auto to FragmentDorSbor
            finish()
        }
        button_less20.setOnClickListener(onButtonClickListener)
        button_in_21_40.setOnClickListener(onButtonClickListener)
        button_more_40.setOnClickListener(onButtonClickListener)


    }
}