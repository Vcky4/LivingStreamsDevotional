package com.ghor.livingstreamsdevotional.ui.nuggets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.FragmentNuggetsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NuggetsFragment : Fragment() {

    private val database: DatabaseReference = Firebase.database.reference
    private val adapter = NuggetAdapter()


    private lateinit var nuggetsViewModel: NuggetsViewModel
    private var _binding: FragmentNuggetsBinding? = null
    private val nuggetsList = mutableListOf<String>()

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

        getNuggets()
        return binding.root
    }



    private fun getNuggets() {
        val ref = database.child("posts").ref
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataValues in dataSnapshot.children) {
                    val game = dataValues.child("nugget").value.toString()
                    nuggetsList.add(game)
                }
                binding.nuggetRecycler.layoutManager = LinearLayoutManager(activity)
                binding.nuggetRecycler.adapter = adapter
                binding.loadingPost.visibility = GONE
                adapter.setUpNuggets(nuggetsList)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}