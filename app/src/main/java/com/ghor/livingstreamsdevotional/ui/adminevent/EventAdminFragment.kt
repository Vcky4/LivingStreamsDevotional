package com.ghor.livingstreamsdevotional.ui.adminevent

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.AddEventLayoutBinding
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminEventBinding
import com.ghor.livingstreamsdevotional.ui.events.EventData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EventAdminFragment : Fragment() {

  private lateinit var eventAdminViewModel: EventAdminViewModel
  private var _binding: FragmentAdminEventBinding? = null
  private val database: DatabaseReference = Firebase.database.reference

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

    val builder = AlertDialog.Builder(context).create()
    val addEventBinding = AddEventLayoutBinding.inflate(layoutInflater)
    builder.setContentView(addEventBinding.root)

    binding.addEvent.setOnClickListener {
      builder.show()
    }

    addEventBinding.postBt.setOnClickListener {
      post(addEventBinding.date.text.toString(), addEventBinding.month.text.toString(),
      addEventBinding.title.text.toString(), addEventBinding.eventDescription.text.toString(),
      addEventBinding.time.text.toString(), addEventBinding.eventVenue.text.toString())
    }
  }

  private fun post(day: String, month : String, title : String, description : String,
                   time : String, venue : String){
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
//        binding.l.visibility = View.GONE
//        binding.postBt.isEnabled = true
        // ...
      }
      .addOnFailureListener {
        // Write failed
        Toast.makeText(context, "post failed!", Toast.LENGTH_SHORT).show()
        // ...
      }

  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}