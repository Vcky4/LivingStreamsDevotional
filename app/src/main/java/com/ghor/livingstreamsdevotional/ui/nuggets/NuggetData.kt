package com.ghor.livingstreamsdevotional.ui.nuggets

import com.google.firebase.database.Exclude

data class NuggetData(val nugget: String, val key: String){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nugget" to nugget
        )
    }

}
