package com.example.instagramclone.domain.use_cases

import com.example.instagramclone.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignOut @Inject constructor(
    private var repository: AuthenticationRepository
){
    operator fun invoke() = repository.firebaseSignOut()
}