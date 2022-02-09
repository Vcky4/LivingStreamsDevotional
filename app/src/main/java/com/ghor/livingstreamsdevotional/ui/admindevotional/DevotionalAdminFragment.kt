package com.ghor.livingstreamsdevotional.ui.admindevotional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminDevotionalBinding

class DevotionalAdminFragment : Fragment() {

  private lateinit var devotionalAdminViewModel: DevotionalAdminViewModel
private var _binding: FragmentAdminDevotionalBinding? = null
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
    val root: View = binding.root

    val textView: TextView = binding.textDashboard
    devotionalAdminViewModel.text.observe(viewLifecycleOwner, {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}