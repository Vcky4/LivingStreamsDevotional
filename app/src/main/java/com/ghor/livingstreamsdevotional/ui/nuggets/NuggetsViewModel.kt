package com.ghor.livingstreamsdevotional.ui.nuggets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NuggetsViewModel : ViewModel() {

    private val _text = MutableLiveData<List<NuggetData>>()

    val nuggets: LiveData<List<NuggetData>> = _text
}