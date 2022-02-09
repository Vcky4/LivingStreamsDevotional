package com.ghor.livingstreamsdevotional.ui.adminauthentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghor.livingstreamsdevotional.databinding.AuthenticationFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
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

        // Initialize Firebase Auth
        auth = Firebase.auth



        return binding.root
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}