package com.example.instagramclone.domain.use_cases

import com.example.instagramclone.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(
    private var repository:AuthenticationRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}