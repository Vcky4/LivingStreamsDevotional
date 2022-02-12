package com.ghor.livingstreamsdevotional.ui.admindevotional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DevotionalAdminViewModel : ViewModel() {

    private val _date = MutableLiveData<String>()
    private val _topic = MutableLiveData<String>()
    private val _scripture = MutableLiveData<String>()
    private val _scriptureBody = MutableLiveData<String>()
    private val _devotionalBody = MutableLiveData<String>()
    private val _actionPoints = MutableLiveData<String>()
    private val _prayer = MutableLiveData<String>()
    private val _nuggets = MutableLiveData<String>()



    val date: LiveData<String> = _date
    val topic: LiveData<String> = _topic
    val scripture: LiveData<String> = _scripture
    val scriptureBody: LiveData<String> = _scriptureBody
    val devotionalBody: LiveData<String> = _devotionalBody
    val actionPoints: LiveData<String> = _actionPoints
    val prayer: LiveData<String> = _prayer
    val nuggets: LiveData<String> = _nuggets

    fun saveDate(date: String){
        _date.value = date
    }
    fun saveTopic(topic: String){
        _topic.value = topic
    }
    fun saveScripture(scripture: String){
        _scripture.value = scripture
    }
    fun saveScriptureBody(scriptureBody: String){
        _scriptureBody.value = scriptureBody
    }
    fun saveDevotionalBody(devotionalBody: String){
        _devotionalBody.value = devotionalBody
    }
    fun saveActionPoints(actionPoints: String){
        _actionPoints.value = actionPoints
    }
    fun savePrayer(prayer: String){
        _prayer.value = prayer
    }
    fun saveNugget(nugget: String){
        _nuggets.value = nugget
    }

}