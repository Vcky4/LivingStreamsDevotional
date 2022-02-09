package com.ghor.livingstreamsdevotional.ui.adminauthentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghor.livingstreamsdevotional.databinding.AuthenticationFragmentBinding

class AuthenticationFragment : Fragment() {

    private lateinit var authenticationViewModel: AuthenticationViewModel
    private var _binding: AuthenticationFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authenticationViewModel =
            ViewModelProvider(this)[AuthenticationViewModel::class.java]

        _binding = AuthenticationFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}