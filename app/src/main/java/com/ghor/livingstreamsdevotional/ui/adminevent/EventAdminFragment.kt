package com.ghor.livingstreamsdevotional.ui.adminevent

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.AddEventLayoutBinding
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminEventBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.ghor.livingstreamsdevotional.ui.events.EventAdapter
import com.ghor.livingstreamsdevotional.ui.events.EventData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EventAdminFragment : Fragment() {

  private lateinit var eventAdminViewModel: EventAdminViewModel
  private var _binding: FragmentAdminEventBinding? = null
  private val database: DatabaseReference = Firebase.database.reference
  private val ref = database.child("event").ref
  private val adapter = EventAdapter()

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    eventAdminViewModel =
      ViewModelProvider(this)[EventAdminViewModel::class.java]

    _binding = FragmentAdminEventBinding.inflate(inflater, container, false)


    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val builder = AlertDialog.Builder(context)
    val addEventBinding = AddEventLayoutBinding.inflate(layoutInflater)
    builder.setView(addEventBinding.root)
    val evenDialog = builder.create()

    binding.addEvent.setOnClickListener {
      evenDialog.show()
    }
    if (Utility.isNetworkAvailable(context)){
      getEvents()
      addEventBinding.postBt.setOnClickListener {
        addEventBinding.loadingDevotional.visibility = VISIBLE
        addEventBinding.postBt.isEnabled = false

        post(addEventBinding.date.text.toString(), addEventBinding.month.text.toString(),
          addEventBinding.title.text.toString(), addEventBinding.eventDescription.text.toString(),
          addEventBinding.time.text.toString(), addEventBinding.eventVenue.text.toString(), addEventBinding)

        evenDialog.dismiss()
      }
    }else{
      Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
    }

  }

  private fun post(day: String, month : String, title : String, description : String,
                   time : String, venue : String, binding: AddEventLayoutBinding){
    val key = database.child("devotional").push().key
    if (key == null) {
      Log.w(ContentValues.TAG, "Couldn't get push key for posts")
      return
    }

    val event = EventData(day,month,title,description,time,venue)
    val postValues = event.toMap()

    val childUpdates = hashMapOf<String, Any>(
      "/event/$key" to postValues
    )

    database.updateChildren(childUpdates)
      .addOnSuccessListener {
        // Write was successful!
        Toast.makeText(context, "posted!", Toast.LENGTH_SHORT).show()
        binding.loadingDevotional.visibility = View.GONE
        binding.postBt.isEnabled = true
        // ...
      }
      .addOnFailureListener {
        // Write failed
        Toast.makeText(context, "post failed!", Toast.LENGTH_SHORT).show()
        // ...
      }

  }

  private fun getEvents() {
    val childEventListener = object : ChildEventListener {
      override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        val menuListener = object : ValueEventListener {
          private val eventList = mutableListOf<EventData>()
          override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (dataValues in dataSnapshot.children) {
              val day = dataValues.child("day").value.toString()
              val month = dataValues.child("month").value.toString()
              val title = dataValues.child("title").value.toString()
              val description = dataValues.child("description").value.toString()
              val time = dataValues.child("time").value.toString()
              val venue = dataValues.child("venue").value.toString()
              eventList.add(EventData(day, month, title, description, time, venue))
              adapter.setUpEvents(eventList)
              binding.loadingEvent.visibility = View.GONE
            }
            binding.eventRecycler.layoutManager = LinearLayoutManager(activity)
            binding.eventRecycler.adapter = adapter

          }

          override fun onCancelled(databaseError: DatabaseError) {
            // handle error
            binding.loadingEvent.visibility = View.GONE
            Toast.makeText(context, "unable to update nuggets", Toast.LENGTH_SHORT).show()

          }
        }
        ref.addListenerForSingleValueEvent(menuListener)
      }

      override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

      override fun onChildRemoved(snapshot: DataSnapshot) {}

      override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

      override fun onCancelled(error: DatabaseError) {
        binding.loadingEvent.visibility = View.GONE
        Toast.makeText(context, "unable to update nuggets", Toast.LENGTH_SHORT).show()

      }

    }

    ref.addChildEventListener(childEventListener)

  }


  override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}