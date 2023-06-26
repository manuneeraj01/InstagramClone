package com.example.instagramclone.presentation.authentication

import androidx.lifecycle.ViewModel
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCases:AuthenticationUseCases
) : ViewModel(){
    val isUserAuthenticated get() = authUseCases.isUserAuthenticated()
}