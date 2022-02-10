package com.ghor.livingstreamsdevotional.ui.nuggets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NuggetsViewModel : ViewModel() {

    private val _text = MutableLiveData<List<NuggetData>>(
        listOf(
            NuggetData("God is love and He loves you no matter what"),
            NuggetData("All things works together for good to all those that love God"),
            NuggetData("You can succeed doing nothing, you must work it!"),
            NuggetData("There is nothing God can not solve"),
            NuggetData("God is love and He loves you no matter what"),
        )
    )

    val nuggets: LiveData<List<NuggetData>> = _text
}