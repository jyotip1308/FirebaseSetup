package com.android.firebasesetup.firebaseAuth.ui

import androidx.lifecycle.ViewModel
import com.android.firebasesetup.firebaseAuth.AuthUser
import com.android.firebasesetup.firebaseAuth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    fun createUser(authUser: AuthUser) = repo.createUser(authUser)

    fun loginUser(authUser: AuthUser) = repo.loginUser(authUser)
}