package com.ghor.livingstreamsdevotional.ui.admindevotional

import com.google.firebase.database.Exclude

data class DevotionalDataModel(val date: String, val topic: String, val qualifier: String,
val scripture: String, val scriptureBody: String, val devotionalBody: String,
val actionPoint: String, val prayer: String, val nugget: String, val morning: String, val evening: String){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "date" to date,
            "topic" to topic,
            "qualifier" to qualifier,
            "scripture" to scripture,
            "scriptureBody" to scriptureBody,
            "devotionalBody" to devotionalBody,
            "actionPoint" to actionPoint,
            "prayer" to prayer,
            "nugget" to nugget,
            "morning" to morning,
            "evening" to evening
        )
    }
}
