package com.ghor.livingstreamsdevotional.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventsViewModel : ViewModel() {

    private val _events = MutableLiveData<List<EventData>>(
        listOf(
            EventData(
                "2", "AUG", "World changers convention",
                "Annual convention",
                "5pm", "God's House Of Refuge, 8 ikot ebido street."
            )
        )
    )
    val events: LiveData<List<EventData>> = _events
}