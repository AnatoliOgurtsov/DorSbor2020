package by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityGruzPricepDetailsStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaGruzPricepDetails : ActivityParent() {

    private lateinit var binding: ActivityGruzPricepDetailsStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityGruzPricepDetailsStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.buttonGruzPricepE1.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonGruzPricepE2.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonGruzPricepE3.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }

    }

    private fun setIntentForResultActivity(autoDetails: String) {
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_AUTO_KIND_GRUZ_PRICEP_DETAILS, autoDetails)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}