package com.ghor.livingstreamsdevotional.ui.adminnugget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghor.livingstreamsdevotional.ui.nuggets.NuggetData

class NuggetAdminViewModel : ViewModel() {


    private val nuggetsList =  mutableListOf<NuggetData>(
        NuggetData("God is love and He loves you no matter what"),
        NuggetData("All things works together for good to all those that love God"),
        NuggetData("You can succeed doing nothing, you must work it!"),
        NuggetData("There is nothing God can not solve"),
        NuggetData("God is love and He loves you no matter what"),
    )
    private val _nuggets = MutableLiveData<List<NuggetData>>(nuggetsList)


    val nuggets: LiveData<List<NuggetData>> = _nuggets

    fun addNugget(nugget: String){
        nuggetsList.add(NuggetData(nugget))
    }
}