package by.a_ogurtsov.AutoTaxes.utilSbor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton

class ActivityKindOfAutoUS : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_us_kind_of_auto

    private lateinit var button_legk_car: MaterialButton
    private lateinit var button_gruz_car: MaterialButton
    private lateinit var button_bus: MaterialButton
    private lateinit var button_dumpTruck: MaterialButton
    private lateinit var button_pricep: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_legk_car = findViewById(R.id.button_legk_car)
        button_gruz_car = findViewById(R.id.button_gruz_car)
        button_bus = findViewById(R.id.button_bus)
        button_pricep = findViewById(R.id.button_pricep)
        button_dumpTruck = findViewById(R.id.button_dumpTruck)



        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_legk_car -> {
                    intent.putExtra("kind_of_auto", "legk_car")
                }
                R.id.button_gruz_car -> {
                    intent.putExtra("kind_of_auto", "gruz_car")
                }
                R.id.button_bus -> {
                    intent.putExtra("kind_of_auto", "bus")
                }
                R.id.button_dumpTruck -> {
                    intent.putExtra("kind_of_auto", "dumpTruck")
                }
                R.id.button_pricep -> {
                    intent.putExtra("kind_of_auto", "pricep")
                }
            }
            setResult(Activity.RESULT_OK, intent)  // pass kind of auto to FragmentUtilSbor
            finish()
        }
        button_legk_car.setOnClickListener(onButtonClickListener)
        button_gruz_car.setOnClickListener(onButtonClickListener)
        button_bus.setOnClickListener(onButtonClickListener)
        button_pricep.setOnClickListener(onButtonClickListener)
        button_dumpTruck.setOnClickListener(onButtonClickListener)
    }
}