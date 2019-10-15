package by.a_ogurtsov.AutoTaxes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton

class ActivityKindOfAuto : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_kind_of_auto

    private lateinit var button_legk_car: MaterialButton
    private lateinit var button_gruz_car: MaterialButton
    private lateinit var button_bus: MaterialButton
    private lateinit var button_pricep: MaterialButton
    private lateinit var button_motocycle: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_legk_car = findViewById(R.id.button_legk_car)
        button_gruz_car = findViewById(R.id.button_gruz_car)
        button_bus = findViewById(R.id.button_bus)
        button_pricep = findViewById(R.id.button_pricep)
        button_motocycle = findViewById(R.id.button_motocycle)


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
                R.id.button_pricep -> {
                    intent.putExtra("kind_of_auto", "pricep")
                }
                R.id.button_motocycle -> {
                    intent.putExtra("kind_of_auto", "motocycle")
                }
            }
            setResult(Activity.RESULT_OK, intent)  // pass kind of auto to FragmentDorSbor
            finish()
        }
        button_legk_car.setOnClickListener(onButtonClickListener)
        button_gruz_car.setOnClickListener(onButtonClickListener)
        button_bus.setOnClickListener(onButtonClickListener)
        button_pricep.setOnClickListener(onButtonClickListener)
        button_motocycle.setOnClickListener(onButtonClickListener)

    }

}