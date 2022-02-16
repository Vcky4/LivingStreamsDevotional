package com.ghor.livingstreamsdevotional.ui.adminevent

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.AddEventLayoutBinding
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminEventBinding

class EventAdminFragment : Fragment() {

  private lateinit var eventAdminViewModel: EventAdminViewModel
private var _binding: FragmentAdminEventBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    eventAdminViewModel =
      ViewModelProvider(this)[EventAdminViewModel::class.java]

    _binding = FragmentAdminEventBinding.inflate(inflater, container, false)


    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val builder = AlertDialog.Builder(context).create()
    val addEventBinding = AddEventLayoutBinding.inflate(layoutInflater)
    builder.setContentView(addEventBinding.root)

    binding.addEvent.setOnClickListener {
      builder.show()
    }
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}