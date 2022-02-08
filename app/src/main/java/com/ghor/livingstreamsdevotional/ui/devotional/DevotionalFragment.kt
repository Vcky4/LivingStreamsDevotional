package com.ghor.livingstreamsdevotional.ui.devotional

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.FragmentDevotionalBinding

class DevotionalFragment : Fragment() {

    private lateinit var devotionalViewModel: DevotionalViewModel
    private var _binding: FragmentDevotionalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        devotionalViewModel =
            ViewModelProvider(this)[DevotionalViewModel::class.java]

        _binding = FragmentDevotionalBinding.inflate(inflater, container, false)


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observe and display date
        devotionalViewModel.dateText.observe(this,{
            binding.dateText.text = it
        })

        //observe and display topic
        devotionalViewModel.topicText.observe(this,{
            binding.topicText.text = it
        })

        //observe and display qualifier
        devotionalViewModel.qualifierText.observe(this,{
            binding.qualifierText.text = "($it)"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}