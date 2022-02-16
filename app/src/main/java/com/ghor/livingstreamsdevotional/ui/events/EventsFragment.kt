package com.ghor.livingstreamsdevotional.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.FragmentEventsBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EventsFragment : Fragment() {

    private lateinit var eventsViewModel: EventsViewModel
    private var _binding: FragmentEventsBinding? = null
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
        eventsViewModel =
            ViewModelProvider(this)[EventsViewModel::class.java]

        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.eventRecycler.layoutManager = LinearLayoutManager(activity)
//        binding.eventRecycler.adapter = adapter
//
//        eventsViewModel.events.observe(viewLifecycleOwner) {
//            adapter.setUpEvents(it)
//        }
        if (Utility.isNetworkAvailable(context)){
            getEvents()
        }else{
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
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