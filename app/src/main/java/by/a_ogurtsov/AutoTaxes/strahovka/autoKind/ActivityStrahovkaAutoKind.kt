package by.a_ogurtsov.AutoTaxes.strahovka.autoKind


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityKindOfAutoStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaAutoKind : ActivityParent() {

    private lateinit var binding: ActivityKindOfAutoStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityKindOfAutoStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners(){
        binding.buttonLegkCarRussia.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkCarNotRussia.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonElectroGibridStrahovka.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonLegkCarPricep.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonGruzCarStrahovka.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonTractor.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonGruzCarPricep.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonMoto.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonBusStrahovka.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
    }

    private fun setIntentForResultActivity(auto: String){
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_AUTO_KIND, auto)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}