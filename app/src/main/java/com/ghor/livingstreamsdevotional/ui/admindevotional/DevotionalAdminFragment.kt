package com.ghor.livingstreamsdevotional.ui.admindevotional

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.ghor.livingstreamsdevotional.databinding.FragmentAdminDevotionalBinding
import com.ghor.livingstreamsdevotional.ui.adminauthentication.Utility

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


//        devotionalAdminViewModel.date.observe(this, {
//            binding.dateText.setText(it)
//        })
//        devotionalAdminViewModel.topic.observe(this, {
//            binding.topicText.setText(it)
//        })
//        devotionalAdminViewModel.scripture.observe(this, {
//            binding.scripture.setText(it)
//        })
//        devotionalAdminViewModel.scriptureBody.observe(this, {
//            binding.scriptureBody.setText(it)
//        })
//        devotionalAdminViewModel.devotionalBody.observe(this, {
//            binding.bodyText.setText(it)
//        })
//        devotionalAdminViewModel.nuggets.observe(this, {
//            binding.nuggetText.setText(it)
//        })
//        devotionalAdminViewModel.prayer.observe(this, {
//            binding.prayerBody.setText(it)
//        })
//        devotionalAdminViewModel.actionPoints.observe(this, {
//            binding.actionPointBody.setText(it)
//        })
//        devotionalAdminViewModel.qualifier.observe(this, {
//            binding.qualifierText.setText(it)
//        })
//        devotionalAdminViewModel.morning.observe(this, {
//            binding.morningText.setText(it)
//        })
//        devotionalAdminViewModel.evening.observe(this, {
//            binding.eveningText.setText(it)
//        })



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //handle clicks
        handleClicks()

        //save text changes
        dataTextWatchers(binding.eveningText)
        dataTextWatchers(binding.morningText)
        dataTextWatchers(binding.dateText)
        dataTextWatchers(binding.bodyText)
        dataTextWatchers(binding.scripture)
        dataTextWatchers(binding.scriptureBody)
        dataTextWatchers(binding.topicText)
        dataTextWatchers(binding.qualifierText)
        dataTextWatchers(binding.actionPointBody)
        dataTextWatchers(binding.prayerBody)
        dataTextWatchers(binding.nuggetText)


    }

    private fun handleClicks() {

        //on click of login button
        binding.postBt.setOnClickListener {

            //here you can check for network availability first, if the network is available, continue
            if (Utility.isNetworkAvailable(context)) {

                //show loading
                binding.loadingPost.visibility = VISIBLE
                binding.postBt.isEnabled = false


            //do the postiong here

            } else {

                Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()

            }

        }

    }


        private fun dataTextWatchers(view: EditText) {
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            override fun afterTextChanged(s: Editable?) {
                when (view) {
                    binding.dateText -> {
                        devotionalAdminViewModel.saveDate(binding.dateText.text.toString())
                    }
                    binding.actionPointBody -> {
                        devotionalAdminViewModel.saveActionPoints(binding.actionPointBody.text.toString())
                    }
                    binding.scripture -> {
                        devotionalAdminViewModel.saveScripture(binding.scripture.text.toString())
                    }
                    binding.scriptureBody -> {
                        devotionalAdminViewModel.saveScriptureBody(binding.scriptureBody.text.toString())
                    }
                    binding.qualifierText -> {
                        devotionalAdminViewModel.saveQualifier(binding.qualifierText.text.toString())
                    }
                    binding.bodyText -> {
                        devotionalAdminViewModel.saveDevotionalBody(binding.bodyText.text.toString())
                    }
                    binding.nuggetText -> {
                        devotionalAdminViewModel.saveNugget(binding.nuggetText.text.toString())
                    }
                    binding.prayerBody -> {
                        devotionalAdminViewModel.savePrayer(binding.prayerBody.text.toString())
                    }
                    binding.topicText -> {
                        devotionalAdminViewModel.saveTopic(binding.topicText.text.toString())
                    }
                    binding.morningText -> {
                        devotionalAdminViewModel.saveMorning(binding.morningText.text.toString())
                    }
                    binding.eveningText -> {
                        devotionalAdminViewModel.saveEvening(binding.evening.text.toString())
                    }
                }
            }

        }
        view.addTextChangedListener(watcher)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}