package by.a_ogurtsov.AutoTaxes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import by.a_ogurtsov.AutoTaxes.databinding.FragmentStartBinding
import by.a_ogurtsov.AutoTaxes.viewModels.MyViewModel


class FragmentStart : Fragment(R.layout.fragment_start), View.OnClickListener {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var model: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this.requireActivity()).get(MyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.buttonOpenFragDorSbor.setOnClickListener(this)
        binding.buttonOpenFragUtilSbor.setOnClickListener(this)
        binding.buttonOpenFragStrahovka.setOnClickListener(this)

        prefShowCurrencyRate()  // show or no currenty rates


        val euroRateObserver = Observer<String> { newValue ->
            binding.textviewEuroRate.text =
                "${resources.getString(R.string.euroRate)} $newValue рублей"
        }
        model.euroRate.observe(viewLifecycleOwner, euroRateObserver)

        val dollarRateObserver = Observer<String> { newValue ->
            binding.textviewDollarRate.text =
                "${resources.getString(R.string.dollarRate)} $newValue рублей"
        }
        model.dollarRate.observe(viewLifecycleOwner, dollarRateObserver)

        return view

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_open_frag_dor_sbor -> {
                Log.d(LOG_TAG, "pressed DorsborKey")
                model.choiceFromFragmentStart.value = FRAGMENTDORSBOR
            }
            R.id.button_open_frag_util_sbor -> {
                Log.d(LOG_TAG, "pressed UtilsborKey")
                model.choiceFromFragmentStart.value = FRAGMENTUTILSBOR
            }
            R.id.button_open_frag_strahovka -> {
                Log.d(LOG_TAG, "pressed Strahovanie")
                model.choiceFromFragmentStart.value = FRAGMENTSTRAHOVKA
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun prefShowCurrencyRate() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        sharedPreferences.booleanLiveData(PREFSHOWCURRENCYRATE, true)
            .observe(viewLifecycleOwner, Observer { value ->
                when (value) {
                    true -> {
                        binding.textviewEuroRate.visibility = VISIBLE
                        binding.textviewDollarRate.visibility = VISIBLE
                    }
                    false -> {
                        binding.textviewEuroRate.visibility = GONE
                        binding.textviewDollarRate.visibility = GONE
                    }
                }
            })
    }

    companion object {
        private val LOG_TAG = "myLogs"
    }
}