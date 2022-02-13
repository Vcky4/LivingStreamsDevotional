package com.ghor.livingstreamsdevotional.ui.adminnugget

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
import com.google.firebase.ktx.Firebase

class NuggetAdminViewModel : ViewModel() {

    private val database: DatabaseReference = Firebase.database.reference

    private val nuggetsList =  mutableListOf<NuggetData>()
    private val _nuggets = MutableLiveData<List<NuggetData>>()

    val nuggets: LiveData<List<NuggetData>> = _nuggets

    fun addNugget(nugget: String){
        nuggetsList.add(NuggetData(nugget))
        database.child("nuggets").setValue(nuggetsList)
    }

    fun readNugget(){
        database.child("nuggets")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    _nuggets.value = dataSnapshot.value as List<NuggetData>?
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("nugget", databaseError.message)
                }
            })
    }
}