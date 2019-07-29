package by.a_ogurtsov.dorsborkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    val currentColor: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}