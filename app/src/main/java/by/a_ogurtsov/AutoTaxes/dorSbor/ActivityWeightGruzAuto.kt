package by.a_ogurtsov.AutoTaxes.dorSbor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton


class ActivityWeightGruzAuto : ActivityParent() {
    private val LAYOUT: Int = R.layout.activity_weight_of_gruz_auto

    private lateinit var button_less_2_5t: MaterialButton
    private lateinit var button_in_2_5_3_5t: MaterialButton
    private lateinit var button_in_3_5_12t: MaterialButton
    private lateinit var button_more_12t: MaterialButton
    private lateinit var button_semi_trailer: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()    // the function from parent activity

        setContentView(LAYOUT)

        button_less_2_5t = findViewById(R.id.button_less_2_5t)
        button_in_2_5_3_5t = findViewById(R.id.button_in_2_5_3_5t)
        button_in_3_5_12t = findViewById(R.id.button_in_3_5_12t)
        button_more_12t = findViewById(R.id.button_more_12t)
        button_semi_trailer = findViewById(R.id.button_semi_trailer)


        val onButtonClickListener = View.OnClickListener { v ->
            val intent: Intent = intent
            when (v.id) {
                R.id.button_less_2_5t -> {
                    intent.putExtra("weight_of_gruz_auto", "less_2_5t")
                }
                R.id.button_in_2_5_3_5t -> {
                    intent.putExtra("weight_of_gruz_auto", "2_5_3_5t")
                }
                R.id.button_in_3_5_12t -> {
                    intent.putExtra("weight_of_gruz_auto", "3_5_12t")
                }
                R.id.button_more_12t -> {
                    intent.putExtra("weight_of_gruz_auto", "more_12t")
                }
                R.id.button_semi_trailer -> {
                    intent.putExtra("weight_of_gruz_auto", "semi_trailer")
                }

            }
            setResult(Activity.RESULT_OK, intent)  // pass kind of auto to FragmentDorSbor
            finish()
        }
        button_less_2_5t.setOnClickListener(onButtonClickListener)
        button_in_2_5_3_5t.setOnClickListener(onButtonClickListener)
        button_in_3_5_12t.setOnClickListener(onButtonClickListener)
        button_more_12t.setOnClickListener(onButtonClickListener)
        button_semi_trailer.setOnClickListener(onButtonClickListener)

    }
}