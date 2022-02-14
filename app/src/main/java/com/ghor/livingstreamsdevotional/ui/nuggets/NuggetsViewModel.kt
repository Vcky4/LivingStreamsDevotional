package com.ghor.livingstreamsdevotional.ui.nuggets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NuggetsViewModel : ViewModel() {

    private val database: DatabaseReference = Firebase.database.reference


    private val _text = MutableLiveData<List<NuggetData>>()

    val nuggets: LiveData<List<NuggetData>> = _text


}