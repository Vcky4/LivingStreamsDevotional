package com.ghor.livingstreamsdevotional.ui.devotional

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.FragmentDevotionalBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DevotionalFragment : Fragment() {

    private lateinit var devotionalViewModel: DevotionalViewModel
    private var _binding: FragmentDevotionalBinding? = null
    private val database: DatabaseReference = Firebase.database.reference


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        devotionalViewModel =
            ViewModelProvider(this)[DevotionalViewModel::class.java]

        _binding = FragmentDevotionalBinding.inflate(inflater, container, false)

        devotionalViewModel.update()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Utility.isNetworkAvailable(context)){
            update()
        }else{
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
        }

    }


    @SuppressLint("SetTextI18n")
    private fun update(){
        val ref = database.child("devotional").ref
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val menuListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (dataValues in dataSnapshot.children) {
                            val body = dataValues.child("devotionalBody").value.toString()
                            val actionPoint = dataValues.child("actionPoint").value.toString()
                            val scripture = dataValues.child("scripture").value.toString()
                            val scriptureBody = dataValues.child("scriptureBody").value.toString()
                            val prayer = dataValues.child("prayer").value.toString()
                            val nugget = dataValues.child("nugget").value.toString()
                            val morning = dataValues.child("morning").value.toString()
                            val evening = dataValues.child("evening").value.toString()
                            val date = dataValues.child("date").value.toString()
                            val topic = dataValues.child("topic").value.toString()
                            val qualifier = dataValues.child("qualifier").value.toString()


                            binding.bodyText.text = body
                            binding.prayerBody.text = prayer
                            binding.actionPointBody.text = actionPoint
                            binding.nuggetText.text = nugget
                            binding.morning.text = "Morning:      $morning"
                            binding.evening.text = "Evening:       $evening"
                            binding.topicText.text = topic
                            binding.qualifierText.text = "($qualifier)"
                            binding.dateText.text = date
                            binding.scripture.text = scripture
                            binding.scriptureBody.text = " '' $scriptureBody '' "
                        }

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