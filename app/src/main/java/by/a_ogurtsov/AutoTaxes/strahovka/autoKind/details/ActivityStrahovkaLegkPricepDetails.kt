package by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityLegkPricepDetailsStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaLegkPricepDetails : ActivityParent() {

    private lateinit var binding: ActivityLegkPricepDetailsStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityLegkPricepDetailsStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.buttonLegkPricepB1Strahovka.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkPricepB2Strahovka.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())        }

    }

    private fun setIntentForResultActivity(autoDetails: String) {
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_AUTO_KIND_LEGK_PRICEP_DETAILS, autoDetails)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}