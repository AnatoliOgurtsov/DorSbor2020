package by.a_ogurtsov.AutoTaxes.strahovka.location


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import by.a_ogurtsov.AutoTaxes.ActivityParent
import by.a_ogurtsov.AutoTaxes.ViewModelFactory
import by.a_ogurtsov.AutoTaxes.databinding.ActivityStrahovkaLocationBinding
import by.a_ogurtsov.AutoTaxes.listLocationsStrahovkaUtils
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import by.a_ogurtsov.AutoTaxes.strahovka.viewModel.ViewModelStrahovka

class ActivityStrahovkaLocation : ActivityParent(), LocationListener {


    private lateinit var binding: ActivityStrahovkaLocationBinding

    private val viewModel: ViewModelStrahovka by viewModels { ViewModelFactory(baseContext) }

    private val locationAdapter = LocationAdapter(this)

    private var k: Float = 0.0F
    private var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView()
        setRecyclerView()

    }

    private fun setRecyclerView() {

        binding.strahovkaLocationRecyclerView.apply {
            locationAdapter.submitList(listLocationsStrahovkaUtils)
            adapter = locationAdapter
        }
    }

    private fun setContentView() {
        binding = ActivityStrahovkaLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun getNameAndСoefficient(name: String, value: Float) {
        city = name
        k = value
        val intent = Intent()
        intent.putExtra(FragmentStrahovka.RESULT_NAME, city)
        intent.putExtra(FragmentStrahovka.RESULT_K, k)
        setResult(Activity.RESULT_OK, intent)  // pass City  to FragmentStrahovka
        finish()
    }
}