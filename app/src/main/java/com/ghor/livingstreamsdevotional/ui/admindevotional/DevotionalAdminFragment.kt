package com.ghor.livingstreamsdevotional.ui.admindevotional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    return binding.root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}