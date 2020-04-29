package by.a_ogurtsov.AutoTaxes.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ViewModelStart(app: Application) : AndroidViewModel(app) {
    val euroValue: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}