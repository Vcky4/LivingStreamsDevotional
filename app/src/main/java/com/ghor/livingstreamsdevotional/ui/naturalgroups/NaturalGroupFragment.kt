package com.ghor.livingstreamsdevotional.ui.naturalgroups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.FragmentNaturalGroupsBinding

class NaturalGroupFragment : Fragment() {

    private lateinit var naturalGroupsViewModel: NaturalGroupsViewModel
    private var _binding: FragmentNaturalGroupsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        naturalGroupsViewModel =
            ViewModelProvider(this)[NaturalGroupsViewModel::class.java]

        _binding = FragmentNaturalGroupsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        naturalGroupsViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}