package com.ghor.livingstreamsdevotional.ui.nuggets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NuggetsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is nuggets Fragment"
    }
    val text: LiveData<String> = _text
}