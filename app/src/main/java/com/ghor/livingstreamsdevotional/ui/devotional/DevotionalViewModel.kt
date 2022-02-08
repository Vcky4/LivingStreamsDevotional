package com.ghor.livingstreamsdevotional.ui.devotional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DevotionalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is devotional Fragment"
    }
    val text: LiveData<String> = _text
}