package com.ghor.livingstreamsdevotional.ui.adminnugget

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghor.livingstreamsdevotional.ui.nuggets.NuggetData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class NuggetAdminViewModel : ViewModel() {

    private val database: DatabaseReference = Firebase.database.reference

    private val nuggetsList =  mutableListOf<String>()
    private val _nuggets = MutableLiveData<List<String>>()

    val nuggets: LiveData<List<String>> = _nuggets


    fun readNugget(){
        database.child("nuggets")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    _nuggets.value = dataSnapshot.value as List<String>?
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("nugget", databaseError.message)
                }
            })
    }


    fun getNuggets() {
        val ref = database.child("posts").ref
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataValues in dataSnapshot.children) {
                    val game = dataValues.child("nugget").value.toString()
                    nuggetsList.add(game)
                }
                _nuggets.value = nuggetsList
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}