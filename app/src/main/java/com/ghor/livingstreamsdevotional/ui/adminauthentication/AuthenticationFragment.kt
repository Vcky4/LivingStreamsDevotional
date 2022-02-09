package com.ghor.livingstreamsdevotional.ui.adminauthentication

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ghor.livingstreamsdevotional.R
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

        handleClicks()
        loginTextWatchers()
        registerTextWatchers()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_authentication_to_navigation_devotional_admin)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerBt.setOnClickListener {


        }

    }


//    private fun checkEmailExistsOrNot(email: String, regNo: String) {
//
//        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
//            Log.d(TAG, "" + (task.result?.signInMethods?.size ?: 0))
//            if (task.result?.signInMethods?.size == 0) {
//                // email not existed
//                binding.loadingUser.visibility = GONE
//                binding.goButton.visibility = GONE
//                binding.registerLayout.visibility = VISIBLE
//            } else {
//                // email existed
//                //login
//                binding.loadingUser.visibility = GONE
//                login(email, regNo)
//
//            }
//        }.addOnFailureListener { e -> e.printStackTrace() }
//    }


    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(context, "login successful!", Toast.LENGTH_LONG)
                        .show()
                    //navigate to home
                    findNavController().navigate(R.id.action_authentication_to_navigation_devotional_admin)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "invalid login details", Toast.LENGTH_SHORT)
                        .show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(
                    context,
                    exception.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(ContentValues.TAG, "createWithEmail:success")
                binding.loadingRegister.visibility = View.GONE
                //save user details
//                    auth.currentUser?.let { authSharedViewModel.writeNewUser(it.uid) }
                Toast.makeText(context, "success!", Toast.LENGTH_SHORT).show()
                //navigate to home
                findNavController().navigate(R.id.action_authentication_to_navigation_devotional_admin)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                binding.loadingRegister.visibility = View.GONE
                Toast.makeText(
                    context,
                    "Registration failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(
                context,
                exception.localizedMessage,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    private fun handleClicks() {

        //on click of login button
        binding.loginBt.setOnClickListener {

            //here you can check for network availability first, if the network is available, continue
            if (Utility.isNetworkAvailable(context)) {

                //show loading
                binding.loadingLogin.visibility = View.VISIBLE
                binding.loginBt.isEnabled = false


                //save to viewModel
//                authSharedViewModel.emailValue(binding.emailText.text.toString())
//                authSharedViewModel.regNoValue(binding.regNoText.text.toString())
                //check if already exist
//                checkEmailExistsOrNot(
//                    binding.emailText.text.toString(),
//                    binding.regNoText.text.toString()
//                )
                login(binding.loginEmail.text.toString(), binding.loginPassword.text.toString())

            } else {

                Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()

            }

        }


        //on click of register button
        binding.registerBt.setOnClickListener {

            //here you can check for network availability first, if the network is available, continue
            if (Utility.isNetworkAvailable(context)) {

                //show loading
                binding.loadingRegister.visibility = View.VISIBLE
                binding.registerBt.isEnabled = false


//                //save to viewModel
//                authSharedViewModel.emailValue(binding.emailText.text.toString())
//                authSharedViewModel.regNoValue(binding.regNoText.text.toString())
                //check if already exist
//                checkEmailExistsOrNot(
//                    binding.emailText.text.toString(),
//                    binding.regNoText.text.toString()
//                )
                register(binding.registerEmail.text.toString(), binding.registerPassword.text.toString())

            } else {

                Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()

            }

        }
    }

    private fun loginTextWatchers() {
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val s1 = binding.loginEmail.text.toString()
                val s2 = binding.loginPassword.text.toString()

                //check if they are empty, make button not clickable
                binding.loginBt.isEnabled = !(s1.isEmpty() || s2.isEmpty())

//                if (s2.length == 2) {
//                    binding.regNoText.text?.append("/")
//                }
            }


            override fun afterTextChanged(s: Editable?) {}

        }
        binding.loginEmail.addTextChangedListener(watcher)
        binding.loginPassword.addTextChangedListener(watcher)
    }

    private fun registerTextWatchers() {
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val s1 = binding.registerEmail.text.toString()
                val s2 = binding.registerPassword.text.toString()

                //check if they are empty, make button not clickable
                binding.registerBt.isEnabled = !(s1.isEmpty() || s2.isEmpty())

//                if (s2.length == 2) {
//                    binding.regNoText.text?.append("/")
//                }
            }


            override fun afterTextChanged(s: Editable?) {}

        }
        binding.registerEmail.addTextChangedListener(watcher)
        binding.registerPassword.addTextChangedListener(watcher)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}