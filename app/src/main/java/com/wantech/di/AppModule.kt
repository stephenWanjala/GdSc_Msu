package com.wantech.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import com.wantech.gdsc_msu.core.domain.repositoryImpl.AuthRepositoryImpl
import com.wantech.gdsc_msu.feature_auth.login.domain.usecase.LoginUseCase
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
}