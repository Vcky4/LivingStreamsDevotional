package com.ghor.livingstreamsdevotional.ui.adminnugget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminNuggetBinding

class NuggetAdminFragment : Fragment() {

  private lateinit var nuggetAdminViewModel: NuggetAdminViewModel
private var _binding: FragmentAdminNuggetBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    nuggetAdminViewModel =
            ViewModelProvider(this).get(NuggetAdminViewModel::class.java)

    _binding = FragmentAdminNuggetBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textHome
    nuggetAdminViewModel.text.observe(viewLifecycleOwner, {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}