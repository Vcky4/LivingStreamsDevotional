package com.ghor.livingstreamsdevotional.ui.devotional

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ghor.livingstreamsdevotional.databinding.FragmentDisplayDevotionalBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DisplayDevotionalFragment : Fragment() {

    private var _binding: FragmentDisplayDevotionalBinding? = null
    private val database: DatabaseReference = Firebase.database.reference


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDisplayDevotionalBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            Log.e("display", "Display did not receive devotional information")
            return
        }
        val args = DisplayDevotionalFragmentArgs.fromBundle(bundle)

        if (Utility.isNetworkAvailable(context)){
            update(args.display.key)
        }else{
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
        }

        binding.feedbackBt.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("ezekiel.atang@yahoo.com")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Devotional Feedback")
                putExtra(Intent.EXTRA_TEXT, "Hello sir, Thanks for being a blessing.")
                // You can also attach multiple items by passing an ArrayList of Uris
            }

            startActivity(intent)

        }


    }


    @SuppressLint("SetTextI18n")
    private fun update(key: String){
        val ref = database.child("devotional").ref
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val menuListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (dataValues in dataSnapshot.children) {
                            if(dataValues.key == key){
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
                        binding.loadingDevotional.visibility = View.GONE

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