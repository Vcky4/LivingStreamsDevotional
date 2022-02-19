package com.ghor.livingstreamsdevotional.ui.adminevent

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.ghor.livingstreamsdevotional.databinding.DeleteDialogBinding
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
    val eventDialog = builder.create()
    eventDialog.setOnDismissListener {
      addEventBinding.eventVenue.text?.clear()
      addEventBinding.eventDescription.text?.clear()
      addEventBinding.title.text?.clear()
      addEventBinding.time.text?.clear()
      addEventBinding.date.text?.clear()
      addEventBinding.link.text?.clear()
      addEventBinding.month.text?.clear()
    }

    val qBuilder = AlertDialog.Builder(context)
    val deleteBinding = DeleteDialogBinding.inflate(layoutInflater)
    qBuilder.setView(deleteBinding.root)
    val deleteDialog = qBuilder.create()

    
    textWatchers(addEventBinding)

    binding.addEvent.setOnClickListener {
      eventDialog.show()
    }
    if (Utility.isNetworkAvailable(context)){
      getEvents()
      addEventBinding.postBt.setOnClickListener {
        addEventBinding.loadingDevotional.visibility = VISIBLE
        addEventBinding.postBt.isEnabled = false

        post(addEventBinding.date.text.toString(), addEventBinding.month.text.toString(),
          addEventBinding.title.text.toString(), addEventBinding.eventDescription.text.toString(),
          addEventBinding.time.text.toString(), addEventBinding.eventVenue.text.toString(),
          addEventBinding.link.text.toString(), "", addEventBinding)

        eventDialog.dismiss()
      }
    }else{
      Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
    }
    adapter.setItemOnLongClickListener { item->
      deleteDialog.show()


      deleteBinding.yesBt.setOnClickListener {

        val menuListener = object : ValueEventListener {
          override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (dataValues in dataSnapshot.children) {
              if (dataValues.key == item.key){
                dataValues.ref.removeValue()
                  .addOnSuccessListener {
                    Toast.makeText(context, "Remove event successful", Toast.LENGTH_SHORT).show()
                  }
              }
            }

          }

          override fun onCancelled(databaseError: DatabaseError) {
            // handle error
            binding.loadingEvent.visibility = View.GONE
            Toast.makeText(context, "unable to update events", Toast.LENGTH_SHORT).show()

          }
        }
        ref.addListenerForSingleValueEvent(menuListener)


        deleteDialog.dismiss()
      }

    }


    deleteBinding.noBt.setOnClickListener {
      deleteDialog.dismiss()
    }

    adapter.setItemOnClickListener { item ->
      if (Utility.isNetworkAvailable(context)){

        eventDialog.show()

        addEventBinding.date.setText(item.day)
        addEventBinding.time.setText(item.time)
        addEventBinding.title.setText(item.title)
        addEventBinding.eventDescription.setText(item.description)
        addEventBinding.month.setText(item.month)
        addEventBinding.link.setText(item.link)
        addEventBinding.eventVenue.setText(item.venue)

        addEventBinding.postBt.setOnClickListener {
          addEventBinding.loadingDevotional.visibility = VISIBLE
          addEventBinding.postBt.isEnabled = false

          post(addEventBinding.date.text.toString(), addEventBinding.month.text.toString(),
            addEventBinding.title.text.toString(), addEventBinding.eventDescription.text.toString(),
            addEventBinding.time.text.toString(), addEventBinding.eventVenue.text.toString(),
            addEventBinding.link.text.toString(), item.key , addEventBinding)

          eventDialog.dismiss()
        }
      }else{
        Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
      }
    }

  }

  private fun post(day: String, month : String, title : String, description : String,
                   time : String, venue : String, link: String, _key: String, binding: AddEventLayoutBinding){
    val key = _key.ifEmpty {
      database.child("devotional").push().key
    }
    if (key == null) {
      Log.w(ContentValues.TAG, "Couldn't get push key for posts")
      return
    }

    val event = EventData(day,month,title,description,time,venue,link, key)
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
              val link = dataValues.child("link").value.toString()
              val key = dataValues.key.toString()
              eventList.add(EventData(day, month, title, description, time, venue, link, key))
              adapter.setUpEvents(eventList)
              binding.loadingEvent.visibility = View.GONE
            }
            binding.eventRecycler.layoutManager = LinearLayoutManager(activity)
            binding.eventRecycler.adapter = adapter

          }

          override fun onCancelled(databaseError: DatabaseError) {
            // handle error
            binding.loadingEvent.visibility = View.GONE
            Toast.makeText(context, "unable to update events", Toast.LENGTH_SHORT).show()

          }
        }
        ref.addListenerForSingleValueEvent(menuListener)
      }

      override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

      override fun onChildRemoved(snapshot: DataSnapshot) {}

      override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

      override fun onCancelled(error: DatabaseError) {
        binding.loadingEvent.visibility = View.GONE
        Toast.makeText(context, "unable to update events", Toast.LENGTH_SHORT).show()

      }

    }

    ref.addChildEventListener(childEventListener)

  }

  private fun textWatchers(binding: AddEventLayoutBinding) {
    val watcher: TextWatcher = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val s0 = binding.date.text.toString()
        val s1 = binding.time.text.toString()
        val s2 = binding.month.text.toString()
        val s3 = binding.title.text.toString()
        val s4 = binding.eventDescription.text.toString()
        val s5 = binding.eventVenue.text.toString()
        binding.postBt.isEnabled =
          !(s0.isEmpty() || s1.isEmpty() || s2.isEmpty() || s3.isEmpty() ||s4.isEmpty() || s5.isEmpty())
      }


      override fun afterTextChanged(s: Editable?) {
      }

    }
    binding.date            .addTextChangedListener(watcher)
    binding.time            .addTextChangedListener(watcher)
    binding.month           .addTextChangedListener(watcher)
    binding.title           .addTextChangedListener(watcher)
    binding.eventDescription.addTextChangedListener(watcher)
    binding.eventVenue      .addTextChangedListener(watcher)
  }



  override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}