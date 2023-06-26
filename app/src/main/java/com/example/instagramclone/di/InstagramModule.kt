package com.example.instagramclone.di

import com.example.instagramclone.data.AuthenticationRepositoryImpl
import com.example.instagramclone.domain.repository.AuthenticationRepository
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases
import com.example.instagramclone.domain.use_cases.FirebaseAuthState
import com.example.instagramclone.domain.use_cases.FirebaseSignIn
import com.example.instagramclone.domain.use_cases.FirebaseSignOut
import com.example.instagramclone.domain.use_cases.FirebaseSignUp
import com.example.instagramclone.domain.use_cases.IsUserAuthenticated
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstagramModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun providesFirebaseFirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providesFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun providesAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore):AuthenticationRepositoryImpl{
        return AuthenticationRepositoryImpl(auth, firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository)
    )
}