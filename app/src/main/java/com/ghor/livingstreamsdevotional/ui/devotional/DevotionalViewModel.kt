package com.ghor.livingstreamsdevotional.ui.devotional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DevotionalViewModel : ViewModel() {
    //private
    private val _qualifierText = MutableLiveData<String>("Light House Fellowship Edition")
    private val _dateText = MutableLiveData<String>("Saturday 01 May")
    private val _topicText = MutableLiveData<String>("wisdom will bring success")

    //public
    val qualifierText: LiveData<String> = _qualifierText
    val dateText: LiveData<String> = _dateText
    val topicText: LiveData<String> = _topicText

    //methods

}