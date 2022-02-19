package com.ghor.livingstreamsdevotional.ui.archive

import java.io.Serializable

data class ArchiveData(val topic: String, val date: String, val scripture: String, val key: String):
        Serializable
