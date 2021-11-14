package by.a_ogurtsov.AutoTaxes.strahovka.term

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.databinding.ActivityTermStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import com.google.android.material.button.MaterialButton

class ActivityStrahovkaTerm : ActivityParent() {
    private lateinit var binding: ActivityTermStrahovkaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setListeners()
    }

    private fun setContentView() {
        binding = ActivityTermStrahovkaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.buttonStrahovkaTerm15days.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm1month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm2month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm3month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm4month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm5month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm6month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm7month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm8month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm9month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm10month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm11month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }
        binding.buttonStrahovkaTerm12month.setOnClickListener {
            setIntentForResultActivity((it as MaterialButton).text.toString())
        }

    }

    private fun setIntentForResultActivity(term: String) {
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_TERM, term)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}