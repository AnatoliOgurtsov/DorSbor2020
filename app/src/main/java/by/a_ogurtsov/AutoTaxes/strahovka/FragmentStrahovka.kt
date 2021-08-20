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
import by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details.*
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
                    resources.getString(R.string.title_button_legk_car_russia) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindLegkRusDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindLegkRusDetailsToSharedPref(viewModel.autoKindLegkRusDetails)
                    }

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
                    resources.getString(R.string.title_button_legk_car_pricep) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindLegkPricepDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindLegkPricepDetailsToSharedPref(viewModel.autoKindLegkPricepDetails)
                    }
                    resources.getString(R.string.title_button_gruz_car_strahovka) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindGruzDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindGruzDetailsToSharedPref(viewModel.autoKindGruzDetails)
                    }
                    resources.getString(R.string.title_button_tractor) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindTractorDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindTractorDetailsToSharedPref(viewModel.autoKindTractorDetails)
                    }
                    resources.getString(R.string.title_button_gruz_car_pricep) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindGruzPricepDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindGruzPricepDetailsToSharedPref(viewModel.autoKindGruzPricepDetails)
                    }
                    resources.getString(R.string.title_button_moto) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindMotoDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindMotoDetailsToSharedPref(viewModel.autoKindMotoDetails)
                    }
                    resources.getString(R.string.title_button_bus_strahovka) -> {
                        bindButtonAutoKindDetailsStrahovka(
                            viewModel.autoKindBusDetails,
                            binding.buttonKindOfAutoDetailsStrahovka
                        )
                        viewModel.putAutoKindBusDetailsToSharedPref(viewModel.autoKindBusDetails)
                    }
                }
            }
        }

    private val activityStrahovkaLegkCarDetails =
        registerForActivityResult(ContractActivityStrahovkaLegkCarDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                when (viewModel.autoKind) {
                    resources.getString(R.string.title_button_legk_car_russia) ->
                        viewModel.putAutoKindLegkRusDetailsToSharedPref(it)
                    resources.getString(R.string.title_button_legk_car_not_russia) ->
                        viewModel.putAutoKindLegkDetailsToSharedPref(it)
                }
            }
        }

    private val activityStrahovkaElectroDetails =
        registerForActivityResult(ContractActivityStrahovkaElectroDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindElectroGibridDetailsToSharedPref(it)
            }
        }

    private val activityStrahovkaLegkPricepDetails =
        registerForActivityResult(ContractActivityStrahovkaLegkPricepDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindLegkPricepDetailsToSharedPref(it)
            }
        }
    private val activityStrahovkaGruzCarDetails =
        registerForActivityResult(ContractActivityStrahovkaGruzCarDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindGruzDetailsToSharedPref(it)
            }
        }

    private val activityStrahovkaTractorDetails =
        registerForActivityResult(ContractActivityStrahovkaTractorDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindTractorDetailsToSharedPref(it)
            }
        }

    private val activityStrahovkaGruzPricepDetails =
        registerForActivityResult(ContractActivityStrahovkaGruzPricepDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindGruzPricepDetailsToSharedPref(it)
            }
        }
    private val activityStrahovkaMotoDetails =
        registerForActivityResult(ContractActivityStrahovkaMotoDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindMotoDetailsToSharedPref(it)
            }
        }
    private val activityStrahovkaBusDetails =
        registerForActivityResult(ContractActivityStrahovkaBusDetails()) {
            if (it != null) {
                bindButtonAutoKindDetailsStrahovka(it, binding.buttonKindOfAutoDetailsStrahovka)
                viewModel.putAutoKindBusDetailsToSharedPref(it)
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
                resources.getString(R.string.title_button_legk_car_pricep) ->
                    activityStrahovkaLegkPricepDetails.launch()
                resources.getString(R.string.title_button_gruz_car_strahovka) ->
                    activityStrahovkaGruzCarDetails.launch()
                resources.getString(R.string.title_button_tractor) ->
                    activityStrahovkaTractorDetails.launch()
                resources.getString(R.string.title_button_gruz_car_pricep) ->
                    activityStrahovkaGruzPricepDetails.launch()
                resources.getString(R.string.title_button_moto) ->
                    activityStrahovkaMotoDetails.launch()
                resources.getString(R.string.title_button_bus_strahovka) ->
                    activityStrahovkaBusDetails.launch()
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
        when (viewModel.autoKind){
            resources.getString(R.string.title_button_legk_car_russia) -> {
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindLegkRusDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            }
            resources.getString(R.string.title_button_legk_car_not_russia) -> {
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindLegkDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            }
            resources.getString(R.string.title_button_electro_gibrid_strahovka) ->
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindElectroGibridDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            resources.getString(R.string.title_button_legk_car_pricep) ->
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindLegkPricepDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            resources.getString(R.string.title_button_gruz_car_strahovka) ->
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindGruzDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            resources.getString(R.string.title_button_tractor) ->
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindTractorDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            resources.getString(R.string.title_button_gruz_car_pricep) ->
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindGruzPricepDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            resources.getString(R.string.title_button_moto) ->
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindMotoDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
            resources.getString(R.string.title_button_bus_strahovka) ->
                bindButtonAutoKindDetailsStrahovka(
                    viewModel.autoKindBusDetails, binding.buttonKindOfAutoDetailsStrahovka
                )
        }
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

