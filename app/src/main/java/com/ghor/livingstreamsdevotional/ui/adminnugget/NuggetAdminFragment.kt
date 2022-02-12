package com.ghor.livingstreamsdevotional.ui.adminnugget

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminNuggetBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility

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
      ViewModelProvider(this)[NuggetAdminViewModel::class.java]

    _binding = FragmentAdminNuggetBinding.inflate(inflater, container, false)

    handleClicks()
    nuggetTextWatchers()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = AdminNuggetAdapter()
    binding.nuggetRecycler.layoutManager = LinearLayoutManager(activity)
    binding.nuggetRecycler.adapter = adapter

    nuggetAdminViewModel.nuggets.observe(this,{
      adapter.setUpNuggets(it)
    })


  }

  private fun handleClicks() {

    //on click of login button
    binding.sendBt.setOnClickListener {

      //here you can check for network availability first, if the network is available, continue
      if (Utility.isNetworkAvailable(context)) {

        nuggetAdminViewModel.addNugget(binding.nuggetText.text.toString())

      } else {

        Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()

      }

    }
  }

  private fun nuggetTextWatchers() {
    val watcher: TextWatcher = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val s1 = binding.nuggetText.text.toString()

        //check if they are empty, make button not clickable
        binding.sendBt.isEnabled = s1.isNotEmpty()

      }


      override fun afterTextChanged(s: Editable?) {}

    }
    binding.nuggetText.addTextChangedListener(watcher)
  }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}