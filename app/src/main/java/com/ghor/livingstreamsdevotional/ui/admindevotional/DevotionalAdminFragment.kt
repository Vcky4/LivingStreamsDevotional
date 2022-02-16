package com.ghor.livingstreamsdevotional.ui.admindevotional

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminDevotionalBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.ghor.livingstreamsdevotional.ui.nuggets.NuggetData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DevotionalAdminFragment : Fragment() {

    private lateinit var devotionalAdminViewModel: DevotionalAdminViewModel
    private var _binding: FragmentAdminDevotionalBinding? = null
    private val database: DatabaseReference = Firebase.database.reference


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        devotionalAdminViewModel =
            ViewModelProvider(this)[DevotionalAdminViewModel::class.java]

        _binding = FragmentAdminDevotionalBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //handle clicks
        handleClicks()




    }

    private fun post(a: String, b : String, c : String, d : String,
                     e : String, f : String, g : String, h : String,
                     i : String, j : String, k : String,){
        val key = database.child("devotional").push().key
        if (key == null) {
            Log.w(ContentValues.TAG, "Couldn't get push key for posts")
            return
        }

        val devotional = DevotionalDataModel(a,b,c,d,e,f,g,h,i,j,k)
        val postValues = devotional.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/devotional/$key" to postValues
        )

        database.updateChildren(childUpdates)
            .addOnSuccessListener {
                // Write was successful!
                Toast.makeText(context, "posted!", Toast.LENGTH_SHORT).show()
                binding.loadingPost.visibility = GONE
                binding.postBt.isEnabled = true
                // ...
            }
            .addOnFailureListener {
                // Write failed
                Toast.makeText(context, "post failed!", Toast.LENGTH_SHORT).show()
                // ...
            }

    }


    private fun handleClicks() {

        //on click of login button
        binding.postBt.setOnClickListener {

            //here you can check for network availability first, if the network is available, continue
            if (Utility.isNetworkAvailable(context)) {

                //show loading
                binding.loadingPost.visibility = VISIBLE
                binding.postBt.isEnabled = false


                //do the posting here
                post(binding.dateText.text.toString(),
                    binding.topicText.text.toString(),
                    binding.qualifierText.text.toString(),
                    binding.scripture.text.toString(),
                    binding.scriptureBody.text.toString(),
                    binding.bodyText.text.toString(),
                    binding.actionPointBody.text.toString(),
                    binding.prayerBody.text.toString(),
                    binding.nuggetText.text.toString(),
                    binding.morningText.text.toString(),
                    binding.eveningText.text.toString()
                )

            } else {

                Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()

            }

        }

    }


    private fun dataTextWatchers(view: EditText) {
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


//                when (view) {
//                    binding.dateText -> {
//                        devotionalAdminViewModel.saveDate(binding.dateText.text.toString())
//                    }
//                    binding.actionPointBody -> {
//                        devotionalAdminViewModel.saveActionPoints(binding.actionPointBody.text.toString())
//                    }
//                    binding.scripture -> {
//                        devotionalAdminViewModel.saveScripture(binding.scripture.text.toString())
//                    }
//                    binding.scriptureBody -> {
//                        devotionalAdminViewModel.saveScriptureBody(binding.scriptureBody.text.toString())
//                    }
//                    binding.qualifierText -> {
//                        devotionalAdminViewModel.saveQualifier(binding.qualifierText.text.toString())
//                    }
//                    binding.bodyText -> {
//                        devotionalAdminViewModel.saveDevotionalBody(binding.bodyText.text.toString())
//                    }
//                    binding.nuggetText -> {
//                        devotionalAdminViewModel.saveNugget(binding.nuggetText.text.toString())
//                    }
//                    binding.prayerBody -> {
//                        devotionalAdminViewModel.savePrayer(binding.prayerBody.text.toString())
//                    }
//                    binding.topicText -> {
//                        devotionalAdminViewModel.saveTopic(binding.topicText.text.toString())
//                    }
//                    binding.morningText -> {
//                        devotionalAdminViewModel.saveMorning(binding.morningText.text.toString())
//                    }
//                    binding.eveningText -> {
//                        devotionalAdminViewModel.saveEvening(binding.evening.text.toString())
//                    }
//                }

            }


            override fun afterTextChanged(s: Editable?) {
            }

        }
        view.addTextChangedListener(watcher)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}