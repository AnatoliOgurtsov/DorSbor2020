package by.a_ogurtsov.AutoTaxes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.a_ogurtsov.AutoTaxes.strahovka.viewModel.ViewModelStrahovka

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val applicationContext: Context?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ViewModelStrahovka::class.java -> ViewModelStrahovka(applicationContext)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}