package com.wantech.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.wantech.gdsc_msu.core.data.GdScPreferences
import com.wantech.gdsc_msu.core.data.GdScPreferences.Companion.GDSC_PREFERENCES
import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import com.wantech.gdsc_msu.core.data.repository.UserDataRepository
import com.wantech.gdsc_msu.core.domain.repositoryImpl.AuthRepositoryImpl
import com.wantech.gdsc_msu.core.domain.repositoryImpl.UserDataRepositoryImpl
import com.wantech.gdsc_msu.feature_auth.login.domain.usecase.LoginUseCase
import com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase.SignUpUseCase
import com.wantech.gdsc_msu.feature_main.profile.data.repository.ProfileRepository
import com.wantech.gdsc_msu.feature_main.profile.domain.repositoryImpl.ProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(
            firebaseAuth = firebaseAuth
        )


    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase =
        SignUpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase =
        LoginUseCase(repository)

    @Provides
    @Singleton
    fun provideDataStorePreferences(app: Application): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                app.preferencesDataStoreFile(GDSC_PREFERENCES)
            }
        )


    @Provides
    @Singleton
    fun provideGdScPreferences(dataStore: DataStore<Preferences>): GdScPreferences =
        GdScPreferences(dataStore)

    @Provides
    @Singleton
    fun provideUserDataRepository(gdScPreferences: GdScPreferences): UserDataRepository =
        UserDataRepositoryImpl(gdScPreferences)

    @Provides
    @Singleton
    fun provideProfileRepository(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage,
        authRepository: AuthRepository
    ): ProfileRepository = ProfileRepositoryImpl(
        fireStore = firestore,
        storage = storage,
        authRepository = authRepository
    )


    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()


}