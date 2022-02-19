package com.ghor.livingstreamsdevotional.ui.nuggets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.FragmentNuggetsBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NuggetsFragment : Fragment() {

    private val database: DatabaseReference = Firebase.database.reference
    private val adapter = NuggetAdapter()
    private val ref = database.child("posts").ref
    private val _nuggets = mutableListOf<String>()


    private lateinit var nuggetsViewModel: NuggetsViewModel
    private var _binding: FragmentNuggetsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nuggetsViewModel =
            ViewModelProvider(this)[NuggetsViewModel::class.java]

        _binding = FragmentNuggetsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Utility.isNetworkAvailable(context)){
            getNuggets()
        }else{
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
        }

    }



    private fun getNuggets() {
        val childEventListener = object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val menuListener = object : ValueEventListener {
                    private val nuggetsList = mutableListOf<NuggetData>()
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (dataValues in dataSnapshot.children) {
                            val nugget = dataValues.child("nugget").value.toString()
                            val key = dataValues.key.toString()
                            nuggetsList.add(NuggetData(nugget, key))
                            adapter.setUpNuggets(nuggetsList)
                            binding.loadingPost.visibility = GONE
                        }
                        binding.nuggetRecycler.layoutManager = LinearLayoutManager(activity)
                        binding.nuggetRecycler.adapter = adapter

                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // handle error
                        binding.loadingPost.visibility = GONE
                        Toast.makeText(context, "unable to update nuggets", Toast.LENGTH_SHORT).show()

                    }
                }
                ref.addListenerForSingleValueEvent(menuListener)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {
                binding.loadingPost.visibility = GONE
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