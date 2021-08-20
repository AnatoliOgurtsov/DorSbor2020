package by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityGruzAutoDetailsStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.databinding.ActivityTractorDetailsStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaTractorDetails : ActivityParent() {

    private lateinit var binding: ActivityTractorDetailsStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityTractorDetailsStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.buttonTractorV1.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonTractorV2.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonTractorV3.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonTractorCrawlerM.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
    }

    private fun setIntentForResultActivity(autoDetails: String) {
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_AUTO_KIND_TRACTOR_DETAILS, autoDetails)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}