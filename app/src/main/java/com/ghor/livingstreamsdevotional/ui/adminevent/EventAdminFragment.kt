package com.ghor.livingstreamsdevotional.ui.adminevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminEventBinding

class EventAdminFragment : Fragment() {

  private lateinit var eventAdimViewModel: EventAdimViewModel
private var _binding: FragmentAdminEventBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    eventAdimViewModel =
            ViewModelProvider(this).get(EventAdimViewModel::class.java)

    _binding = FragmentAdminEventBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textNotifications
    eventAdimViewModel.text.observe(viewLifecycleOwner, {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}