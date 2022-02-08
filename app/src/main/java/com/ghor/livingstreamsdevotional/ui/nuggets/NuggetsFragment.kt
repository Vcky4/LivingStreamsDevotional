package com.ghor.livingstreamsdevotional.ui.nuggets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        val root: View = binding.root

        val textView: TextView = binding.textHome
        nuggetsViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}