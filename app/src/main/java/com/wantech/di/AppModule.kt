package com.wantech.di

import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.sign_up.data.repository.SignUpUserRepository
import com.wantech.gdsc_msu.feature_auth.sign_up.domain.repositoryImpl.SignUpUserRepositoryImpl
import com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase.SignUpUseCase
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
    fun provideSignUpUserRepository(auth:FirebaseAuth): SignUpUserRepository {
        return SignUpUserRepositoryImpl(auth = auth)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(repository: SignUpUserRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }
}