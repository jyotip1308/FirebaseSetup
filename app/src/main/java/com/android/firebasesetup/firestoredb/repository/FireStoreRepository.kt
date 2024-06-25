package com.android.firebasesetup.firestoredb.repository

import com.android.firebasesetup.firestoredb.FireStoreModelResponse
import com.android.firebasesetup.utility.ResultState
import kotlinx.coroutines.flow.Flow

interface FireStoreRepository {

    fun insert(
        item:FireStoreModelResponse.FireStoreItem
    ) : Flow<ResultState<String>>

    fun getItems(): Flow<ResultState<List<FireStoreModelResponse>>>

    fun delete(key:String): Flow<ResultState<String>>

    fun update(
        item: FireStoreModelResponse
    ) : Flow<ResultState<String>>
}