package com.ghor.livingstreamsdevotional.ui.nuggets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.FragmentNuggetsBinding

class NuggetsFragment : Fragment() {

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

        nuggetsViewModel.getNuggets()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NuggetAdapter()
        binding.nuggetRecycler.layoutManager = LinearLayoutManager(activity)
        binding.nuggetRecycler.adapter = adapter

        nuggetsViewModel.nuggets.observe(this,{
            adapter.setUpNuggets(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}