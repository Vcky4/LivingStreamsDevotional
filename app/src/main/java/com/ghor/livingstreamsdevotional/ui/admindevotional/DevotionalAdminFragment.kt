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

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    devotionalAdminViewModel.date.observe(this,{
      binding.dateText.setText(it)
    })
     devotionalAdminViewModel.topic.observe(this,{
      binding.topicText.setText(it)
    })
     devotionalAdminViewModel.scripture.observe(this,{
      binding.scripture.setText(it)
    })
     devotionalAdminViewModel.scriptureBody.observe(this,{
      binding.scriptureBody.setText(it)
    })
     devotionalAdminViewModel.devotionalBody.observe(this,{
      binding.bodyText.setText(it)
    })
     devotionalAdminViewModel.nuggets.observe(this,{
      binding.nuggetText.setText(it)
    })
     devotionalAdminViewModel.prayer.observe(this,{
      binding.prayerBody.setText(it)
    })
    devotionalAdminViewModel.actionPoints.observe(this,{
      binding.actionPointBody.setText(it)
    })
    devotionalAdminViewModel.qualifier.observe(this,{
      binding.qualifierText.setText(it)
    })




  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}