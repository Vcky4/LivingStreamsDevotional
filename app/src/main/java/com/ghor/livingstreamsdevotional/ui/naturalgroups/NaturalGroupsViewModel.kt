package com.ghor.livingstreamsdevotional.ui.naturalgroups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NaturalGroupsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is natural group Fragment"
    }
    val text: LiveData<String> = _text
}