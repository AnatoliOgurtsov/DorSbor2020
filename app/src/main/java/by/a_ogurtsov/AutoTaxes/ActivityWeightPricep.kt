package by.a_ogurtsov.AutoTaxes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton


class ActivityWeightPricep : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_weight_of_pricep

    private lateinit var button_less_0_75: MaterialButton
    private lateinit var button_more_0_75: MaterialButton
    private lateinit var button_dacha: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_less_0_75 = findViewById(R.id.button_less_0_75)
        button_more_0_75 = findViewById(R.id.button_more_0_75)
        button_dacha = findViewById(R.id.button_dacha)

        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_less_0_75 -> {
                    intent.putExtra("weight_of_pricep", "less_0_75")
                }
                R.id.button_more_0_75 -> {
                    intent.putExtra("weight_of_pricep", "more_0_75")
                }
                R.id.button_dacha -> {
                    intent.putExtra("weight_of_pricep", "dacha")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass kind of auto to FragmentDorSbor
            finish()
        }
        button_less_0_75.setOnClickListener(onButtonClickListener)
        button_more_0_75.setOnClickListener(onButtonClickListener)
        button_dacha.setOnClickListener(onButtonClickListener)


    }
}