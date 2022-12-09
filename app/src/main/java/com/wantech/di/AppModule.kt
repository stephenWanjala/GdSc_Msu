package com.wantech.di

import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.login.data.repository.LoginUserRepository
import com.wantech.gdsc_msu.feature_auth.login.domain.repositoryImpl.LoginRepositoryImpl
import com.wantech.gdsc_msu.feature_auth.login.domain.usecase.LoginUseCase
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
    fun provideSignUpUserRepository(auth: FirebaseAuth): SignUpUserRepository {
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

    @Provides
    @Singleton
    fun provideLoginUserRepository(auth: FirebaseAuth): LoginUserRepository =
        LoginRepositoryImpl(auth)

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: LoginUserRepository): LoginUseCase =
        LoginUseCase(repository)
}