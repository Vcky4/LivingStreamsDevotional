package com.ghor.livingstreamsdevotional.ui.adminnugget

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminNuggetBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.ghor.livingstreamsdevotional.ui.nuggets.NuggetData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NuggetAdminFragment : Fragment() {

  private val database: DatabaseReference = Firebase.database.reference
  private lateinit var nuggetAdminViewModel: NuggetAdminViewModel
private var _binding: FragmentAdminNuggetBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    nuggetAdminViewModel =
      ViewModelProvider(this)[NuggetAdminViewModel::class.java]

    _binding = FragmentAdminNuggetBinding.inflate(inflater, container, false)

    nuggetTextWatchers()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = AdminNuggetAdapter()
    binding.nuggetRecycler.layoutManager = LinearLayoutManager(activity)
    binding.nuggetRecycler.adapter = adapter

    nuggetAdminViewModel.nuggets.observe(this,{
      if(it != null){
        adapter.setUpNuggets(it)
      }
    })


    //on click of send button
    binding.sendBt.setOnClickListener {

      //here you can check for network availability first, if the network is available, continue
      if (Utility.isNetworkAvailable(context)) {

        nuggetAdminViewModel.addNugget(binding.nuggetText.text.toString())

        nuggetAdminViewModel.readNugget()
        binding.nuggetText.text?.clear()


        nuggetAdminViewModel.nuggets.observe(this,{
          AdminNuggetAdapter().setUpNuggets(it)
        })

      } else {

        Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()

      }

    }


  }

  private fun handleClicks() {

    //on click of send button
    binding.sendBt.setOnClickListener {

      //here you can check for network availability first, if the network is available, continue
      if (Utility.isNetworkAvailable(context)) {

        addNugget(binding.nuggetText.text.toString())
//        nuggetAdminViewModel.addNugget(binding.nuggetText.text.toString())

        nuggetAdminViewModel.readNugget()
        binding.nuggetText.text?.clear()


//        nuggetAdminViewModel.nuggets.observe(this,{
//          AdminNuggetAdapter().setUpNuggets(it)
//        })

      } else {

        Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()

      }

    }
  }

  private fun addNugget(nugget: String){
    val key = database.child("posts").push().key
    if (key == null) {
      Log.w(ContentValues.TAG, "Couldn't get push key for posts")
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
        Toast.makeText(context, "posted!", Toast.LENGTH_SHORT).show()
        // ...
      }
      .addOnFailureListener {
        // Write failed
        Toast.makeText(context, "post failed!", Toast.LENGTH_SHORT).show()
        // ...
      }

  }

  private fun nuggetTextWatchers() {
    val watcher: TextWatcher = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val s1 = binding.nuggetText.text.toString()

        //check if they are empty, make button not clickable
        binding.sendBt.isEnabled = s1.isNotEmpty()

      }


      override fun afterTextChanged(s: Editable?) {}

    }
    binding.nuggetText.addTextChangedListener(watcher)
  }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}