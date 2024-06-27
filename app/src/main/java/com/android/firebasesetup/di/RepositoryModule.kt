package com.android.firebasesetup.di

import com.android.firebasesetup.firebaseAuth.repository.AuthRepository
import com.android.firebasesetup.firebaseAuth.repository.AuthRepositoryImpl
import com.android.firebasesetup.firebaseRealtimeDb.repository.RealtimeDbRepository
import com.android.firebasesetup.firebaseRealtimeDb.repository.RealtimeRepository
import com.android.firebasesetup.firestoredb.repository.FireStoreDbRepositoryImpl
import com.android.firebasesetup.firestoredb.repository.FireStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRealtimeRepository(
        repo:RealtimeDbRepository
    ) : RealtimeRepository

    @Binds
    abstract fun providesFireStoreRepository(
        repo:FireStoreDbRepositoryImpl
    ) : FireStoreRepository

    @Binds
    abstract fun providesFirebaseAuthRepository(
        repo:AuthRepositoryImpl
    ) : AuthRepository
}