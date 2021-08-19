package by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityLegkAutoDetailsStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaLegkCarDetails : ActivityParent() {

    private lateinit var binding: ActivityLegkAutoDetailsStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityLegkAutoDetailsStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.buttonLegkCarLess1200.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkCarIn12001800.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkCarIn18002500.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkCarIn25003500.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkCarMore3500.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkCarTaxi.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
    }

    private fun setIntentForResultActivity(autoDetails: String) {
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_AUTO_KIND_DETAILS, autoDetails)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}