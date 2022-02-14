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

    private val nuggetsList = mutableListOf<String>()

    private val _text = MutableLiveData<List<String>>()

    val nuggets: LiveData<List<String>> = _text

    fun getNuggets() {
        val ref = database.child("posts").ref
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataValues in dataSnapshot.children) {
                    val game = dataValues.child("nugget").value.toString()
                    nuggetsList.add(game)
                }
               _text.value = nuggetsList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}