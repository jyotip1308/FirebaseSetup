package com.android.firebasesetup.firebaseAuth.repository

import android.app.Activity
import com.android.firebasesetup.firebaseAuth.AuthUser
import com.android.firebasesetup.utility.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>

    fun loginUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>

    fun createUserWithPhone(
        phone: String,
        activity:Activity
    ): Flow<ResultState<String>>

    fun signWithCredential(
        otp:String
    ): Flow<ResultState<String>>
}