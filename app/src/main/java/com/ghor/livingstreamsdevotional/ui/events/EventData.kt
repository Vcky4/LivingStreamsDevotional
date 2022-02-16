package com.ghor.livingstreamsdevotional.ui.events

import com.google.firebase.database.Exclude

data class EventData(val day: String, val month: String, val title: String,
                     val description: String, val time: String, val venue: String){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "day" to day,
            "month" to month,
            "title" to title,
            "description" to description,
            "time" to time,
            "venue" to venue )
    }
}
