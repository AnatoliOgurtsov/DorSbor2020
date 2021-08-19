package by.a_ogurtsov.AutoTaxes.strahovka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.a_ogurtsov.AutoTaxes.*
import by.a_ogurtsov.AutoTaxes.databinding.FragmentStrahovkaBinding
import by.a_ogurtsov.AutoTaxes.strahovka.autoKind.ContractActivityStrahovkaAutoKind
import by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details.ContractActivityStrahovkaElectroDetails
import by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details.ContractActivityStrahovkaLegkCarDetails
import by.a_ogurtsov.AutoTaxes.strahovka.location.ContractActivityStrahovkaLocation
import by.a_ogurtsov.AutoTaxes.strahovka.viewModel.ViewModelStrahovka
import com.google.android.material.button.MaterialButton

class FragmentStrahovka : Fragment() {

    private var _binding: FragmentStrahovkaBinding? = null
    private val binding get() = _binding!!

    private val color: String = ""
    private val widthScreenDPI: String = ""

    private val viewModel: ViewModelStrahovka by viewModels { ViewModelFactory(context) }

    private val activityStrahovkaLocation =
        registerForActivityResult(ContractActivityStrahovkaLocation()) {
            if (it != null) {
                bindButtonLocationStrahovka(
                    it.extras?.getString(RESULT_NAME),
                    it.extras?.getFloat(RESULT_K, 0.0F),
                    binding.buttonStrahovkaLocation
                )
                viewModel.putLocationToSharedPref(
                    it.extras?.getString(RESULT_NAME)!!,
                    it.extras?.getFloat(RESULT_K, 0.0F)!!
                )
            }
        }

    private val activityStrahovkaAutoKind =
        registerForActivityResult(ContractActivityStrahovkaAutoKind()) {
            if (it != null) {
                bindButtonAutoKindStrahovka(it, binding.buttonKindOfAutoStrahovka)
                viewModel.putAutoKindToSharedPref(it)
                when (it) {
                    resources.getString(R.string.title_button_legk_car_russia),
                    resources.getString(R.string.title_button_legk_car_not_russia) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindLegkDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindLegkDetailsToSharedPref(viewModel.autoKindLegkDetails)
                    }
                    resources.getString(R.string.title_button_electro_gibrid_strahovka) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindElectroGibridDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindElectroGibridDetailsToSharedPref(viewModel.autoKindElectroGibridDetails)
                    }

                }

            }
        }

    private val activityStrahovkaLegkCarDetails =
        registerForActivityResult(ContractActivityStrahovkaLegkCarDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindLegkDetailsToSharedPref(it)
            }
        }
    private val activityStrahovkaElectroDetails =
        registerForActivityResult(ContractActivityStrahovkaElectroDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindElectroGibridDetailsToSharedPref(it)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStrahovkaBinding.inflate(inflater, container, false)

        setButtonWidth(binding.buttonStrahovkaLocation)  // get width of button according screen width
        setButtonWidth(binding.buttonKindOfAutoStrahovka)
        setButtonWidth(binding.buttonKindOfAutoDetailsStrahovka)
        setListeners(binding)
        viewModel.initSpref()
        setObservers()
        setStartSettingbuttons()

        return binding.root
    }

    private fun setListeners(binding: FragmentStrahovkaBinding) {

        binding.buttonStrahovkaLocation.setOnClickListener {
            activityStrahovkaLocation.launch()
        }

        binding.buttonKindOfAutoStrahovka.setOnClickListener {
            activityStrahovkaAutoKind.launch()
        }

        binding.buttonKindOfAutoDetailsStrahovka.setOnClickListener {
            when (viewModel.autoKind) {
                resources.getString(R.string.title_button_legk_car_russia),
                resources.getString(R.string.title_button_legk_car_not_russia) -> {
                    activityStrahovkaLegkCarDetails.launch()
                }
                resources.getString(R.string.title_button_electro_gibrid_strahovka) ->
                    activityStrahovkaElectroDetails.launch()
            }
        }

    }

    private fun setObservers() {
        viewModel.totalSumValue.observe(this.viewLifecycleOwner) {
            setDataScreen(it)
        }
    }

    private fun setDataScreen(data: String) {
        binding.strahovkaTextViewSumsValue.text = data
    }

    private fun setButtonWidth(button: MaterialButton) {
        button.layoutParams.width =
            requireArguments().getInt(widthScreenDPI) - 80   // устанавливаем ширину кнопки без группы
    }

    private fun setStartSettingbuttons() {
        bindButtonLocationStrahovka(
            viewModel.locationName,
            viewModel.locationK,
            binding.buttonStrahovkaLocation
        )
        bindButtonAutoKindStrahovka(
            viewModel.autoKind, binding.buttonKindOfAutoStrahovka
        )
        bindButtonAutoKindDetailsStrahovka(
            viewModel.autoKindLegkDetails, binding.buttonKindOfAutoDetailsStrahovka
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: Int): FragmentStrahovka {
            val fragment = FragmentStrahovka().apply {
                arguments = Bundle().apply {
                    putString(color, param1)
                    putInt(widthScreenDPI, param2)
                }
            }
            return fragment
        }

        const val RESULT_K = "RESULT_K"
        const val RESULT_NAME = "RESULT_NAME"
        const val RESULT_AUTO_KIND = "RESULT_AUTO_KIND"
        const val RESULT_AUTO_KIND_DETAILS = "RESULT_AUTO_KIND_DETAILS"
        const val RESULT_AUTO_KIND_ELECTRO_GIBRID_DETAILS =
            "RESULT_AUTO_KIND_ELECTRO_GIBRID_DETAILS"
        const val RESULT_AUTO_KIND_LEGK_PRICEP_DETAILS = "RESULT_AUTO_KIND_LEGK_PRICEP_DETAILS"
        const val RESULT_AUTO_KIND_GRUZ_DETAILS = "RESULT_AUTO_KIND_GRUZ_DETAILS"
        const val RESULT_AUTO_KIND_TRACTOR_DETAILS = "RESULT_AUTO_KIND_TRACTOR_DETAILS"
        const val RESULT_AUTO_KIND_GRUZ_PRICEP_DETAILS = "RESULT_AUTO_KIND_GRUZ_PRICEP_DETAILS"
        const val RESULT_AUTO_KIND_MOTO_DETAILS = "RESULT_AUTO_KIND_MOTO_DETAILS"
        const val RESULT_AUTO_KIND_BUS_DETAILS = "RESULT_AUTO_KIND_BUS_DETAILS"


    }

}

