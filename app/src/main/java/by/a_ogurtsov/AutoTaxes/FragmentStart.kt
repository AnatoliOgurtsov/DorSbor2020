package by.a_ogurtsov.AutoTaxes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.a_ogurtsov.AutoTaxes.databinding.FragmentStartBinding
import by.a_ogurtsov.AutoTaxes.viewModels.MyViewModel


class FragmentStart : Fragment(R.layout.fragment_start), View.OnClickListener {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    val LOG_TAG = "myLogs"
    private lateinit var model: MyViewModel
    val FRAGMENTDORSBOR = "FRAGMENT_DORSBOR"
    val FRAGMENTUTILSBOR = "FRAGMENT_UTILSBOR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this.activity!!).get(MyViewModel::class.java)


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


        val euroRateObserver = Observer<String> { newValue ->
            binding.textviewEuroRate.text = "${resources.getString(R.string.euroRate)} $newValue рублей"
        }
        model.euroRate.observe(this, euroRateObserver)
        return view
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.button_open_frag_dor_sbor -> {
                Log.d(LOG_TAG, "pressed DorsborKey")
                model.choice_from_fragmentStart.value = FRAGMENTDORSBOR
            }
            R.id.button_open_frag_util_sbor -> {
                Log.d(LOG_TAG, "pressed UtilsborKey")
                model.choice_from_fragmentStart.value = FRAGMENTUTILSBOR
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}