package com.ghor.livingstreamsdevotional.ui.archive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.R
import com.ghor.livingstreamsdevotional.databinding.FragmentArchiveBinding
import com.ghor.livingstreamsdevotional.databinding.FragmentDevotionalBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.ghor.livingstreamsdevotional.ui.devotional.DevotionalViewModel
import com.ghor.livingstreamsdevotional.ui.events.EventData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ArchiveFragment : Fragment() {
    private var _binding: FragmentArchiveBinding? = null
    private val database: DatabaseReference = Firebase.database.reference
    private val adapter = ArchiveAdapter()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArchiveBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Utility.isNetworkAvailable(context)){
            update()
        }else{
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
        }

        adapter.setItemOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("display", it)
            }
            findNavController().navigate(R.id.action_navigation_archive_to_displayDevotionalFragment, bundle)

        }

    }


    @SuppressLint("SetTextI18n")
    private fun update(){
        val ref = database.child("devotional").ref
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val menuListener = object : ValueEventListener {
                    private val archiveList = mutableListOf<ArchiveData>()
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (dataValues in dataSnapshot.children) {
                            val scripture = dataValues.child("scripture").value.toString()
                            val date = dataValues.child("date").value.toString()
                            val topic = dataValues.child("topic").value.toString()
                            val key = dataValues.key.toString()
                            Log.d("KEY",key)

                            archiveList.add(ArchiveData(topic,date,scripture,key))
                            adapter.setUpArchive(archiveList)
                            binding.loadingArchive.visibility = View.GONE
                        }
                        binding.archiveRecycler.layoutManager = LinearLayoutManager(activity)
                        binding.archiveRecycler.adapter = adapter
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // handle error
                    }
                }
                ref.addListenerForSingleValueEvent(menuListener)            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}

        }

        ref.addChildEventListener(childEventListener)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}