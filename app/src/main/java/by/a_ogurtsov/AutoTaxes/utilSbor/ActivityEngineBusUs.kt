package by.a_ogurtsov.AutoTaxes.utilSbor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton


class ActivityEngineBusUs : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_us_bus_engine

    private lateinit var button_less_2500_us_bus: MaterialButton
    private lateinit var button_in_2500_5000_us_bus: MaterialButton
    private lateinit var button_in_5000_100000_us_bus: MaterialButton
    private lateinit var button_more_10000_us_bus: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_less_2500_us_bus = findViewById(R.id.button_less_2500_us_bus)
        button_in_2500_5000_us_bus = findViewById(R.id.button_in_2500_5000_us_bus)
        button_in_5000_100000_us_bus = findViewById(R.id.button_in_5000_10000_us_bus)
        button_more_10000_us_bus = findViewById(R.id.button_more_10000_us_bus)


        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_less_2500_us_bus -> {
                    intent.putExtra("us_engineOfBus", "less_2500")
                }
                R.id.button_in_2500_5000_us_bus -> {
                    intent.putExtra("us_engineOfBus", "2500_5000")
                }
                R.id.button_in_5000_10000_us_bus -> {
                    intent.putExtra("us_engineOfBus", "5000_10000")
                }
                R.id.button_more_10000_us_bus -> {
                    intent.putExtra("us_engineOfBus", "more_10000")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass engine of bus to FragmentUtilSbor
            finish()
        }
        button_less_2500_us_bus.setOnClickListener(onButtonClickListener)
        button_in_2500_5000_us_bus.setOnClickListener(onButtonClickListener)
        button_in_5000_100000_us_bus.setOnClickListener(onButtonClickListener)
        button_more_10000_us_bus.setOnClickListener(onButtonClickListener)

    }
}