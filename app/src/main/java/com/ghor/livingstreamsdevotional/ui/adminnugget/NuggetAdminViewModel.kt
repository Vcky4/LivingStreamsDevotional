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

    private val nuggetsList =  mutableListOf<NuggetData>()
    private val _nuggets = MutableLiveData<List<NuggetData>>()

    val nuggets: LiveData<List<NuggetData>> = _nuggets

    fun addNugget(nugget: String){
        val key = database.child("posts").push().key
        if (key == null) {
            Log.w(TAG, "Couldn't get push key for posts")
            return
        }

        val post = NuggetData(nugget)
        val postValues = post.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/posts/$key" to postValues,
            "/user-posts/$key" to postValues
        )

        database.updateChildren(childUpdates)
            .addOnSuccessListener {
                // Write was successful!
                // ...
            }
            .addOnFailureListener {
                // Write failed
                // ...
            }

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