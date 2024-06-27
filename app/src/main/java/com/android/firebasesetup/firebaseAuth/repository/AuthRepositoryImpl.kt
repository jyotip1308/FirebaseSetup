package com.android.firebasesetup.firebaseAuth.repository

import android.util.Log
import com.android.firebasesetup.firebaseAuth.AuthUser
import com.android.firebasesetup.utility.ResultState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authdb:FirebaseAuth
) : AuthRepository {

    override fun createUser(auth: AuthUser): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        authdb.createUserWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnCompleteListener{
            if (it.isSuccessful){
                trySend(ResultState.Success("User created successfully"))
                Log.d("Main", "Current user ${authdb.currentUser?.uid} ")
            }
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }

        awaitClose{
            close()
        }
    }

    override fun loginUser(auth: AuthUser): Flow<ResultState<String>> = callbackFlow {
       trySend(ResultState.Loading)

        authdb.signInWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnSuccessListener {
            trySend(ResultState.Success("Login Successfully"))
            Log.d("Main", "Current user ${authdb.currentUser?.uid} ")
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }

        awaitClose {
            close()
        }
    }
}