package by.a_ogurtsov.AutoTaxes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    val currentColor: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val choice_from_fragmentStart: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

}