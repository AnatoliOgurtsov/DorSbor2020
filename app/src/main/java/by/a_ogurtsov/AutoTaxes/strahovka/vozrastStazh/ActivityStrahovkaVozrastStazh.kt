package by.a_ogurtsov.AutoTaxes.strahovka.vozrastStazh

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityVozrastStazhStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaVozrastStazh : ActivityParent() {
    private lateinit var binding: ActivityVozrastStazhStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityVozrastStazhStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.buttonStrahovkaVozrastStazhLess25Less2.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaVozrastStazhLess25More2.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaVozrastStazhMore25Less2.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaVozrastStazhMore25More2.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
    }

    private fun setIntentForResultActivity(vozrastStazh: String) {
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_VOZRAST_STAZH, vozrastStazh)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}