package com.ghor.livingstreamsdevotional.ui.adminevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventAdimViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}