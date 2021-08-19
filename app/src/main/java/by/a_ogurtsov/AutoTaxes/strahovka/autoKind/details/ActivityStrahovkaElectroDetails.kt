package by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityElectroDetailsStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaElectroDetails : ActivityParent() {

    private lateinit var binding: ActivityElectroDetailsStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityElectroDetailsStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.buttonElectroStrahovka.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonGibridStrahovka.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())        }

    }

    private fun setIntentForResultActivity(autoDetails: String) {
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_AUTO_KIND_ELECTRO_GIBRID_DETAILS, autoDetails)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}