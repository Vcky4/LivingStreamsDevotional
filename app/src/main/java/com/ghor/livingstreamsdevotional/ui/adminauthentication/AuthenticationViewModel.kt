package com.ghor.livingstreamsdevotional.ui.adminauthentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthenticationViewModel : ViewModel() {
    //private
    private val _email = MutableLiveData<String>()


    //public
    val email: MutableLiveData<String> = _email
}