package com.android.firebasesetup.firebaseRealtimeDb.repository

import com.android.firebasesetup.firebaseRealtimeDb.RealtimeModelResponse
import com.android.firebasesetup.utility.ResultState
import kotlinx.coroutines.flow.Flow

interface RealtimeRepository {

    fun insert(
        item:RealtimeModelResponse.RealtimeItems
    ) : Flow<ResultState<String>>

    fun getItems(): Flow<ResultState<List<RealtimeModelResponse>>>

    fun delete(
        key: String
    ): Flow<ResultState<String>>

    fun update(
        res: RealtimeModelResponse
    ): Flow<ResultState<String>>
}