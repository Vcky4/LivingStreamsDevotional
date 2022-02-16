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
    private val _qualifier = MutableLiveData<String>()
    private val _morning = MutableLiveData<String>()
    private val _evening = MutableLiveData<String>()


    val date  = _date
    val topic = _topic.value
    val scripture = _scripture.value
    val scriptureBody = _scriptureBody.value
    val devotionalBody = _devotionalBody.value
    val actionPoints = _actionPoints.value
    val prayer = _prayer.value
    val nuggets = _nuggets.value
    val qualifier = _qualifier.value
    val morning = _morning.value
    val evening = _evening.value

    fun saveDate(date: String) {
        _date.value = date
    }

    fun saveTopic(topic: String) {
        _topic.value = topic
    }

    fun saveScripture(scripture: String) {
        _scripture.value = scripture
    }

    fun saveScriptureBody(scriptureBody: String) {
        _scriptureBody.value = scriptureBody
    }

    fun saveDevotionalBody(devotionalBody: String) {
        _devotionalBody.value = devotionalBody
    }

    fun saveActionPoints(actionPoints: String) {
        _actionPoints.value = actionPoints
    }

    fun savePrayer(prayer: String) {
        _prayer.value = prayer
    }

    fun saveNugget(nugget: String) {
        _nuggets.value = nugget
    }

    fun saveQualifier(qualifier: String) {
        _qualifier.value = qualifier
    }

    fun saveMorning(morning: String) {
        _morning.value = morning
    }

    fun saveEvening(evening: String) {
        _evening.value = evening
    }


}