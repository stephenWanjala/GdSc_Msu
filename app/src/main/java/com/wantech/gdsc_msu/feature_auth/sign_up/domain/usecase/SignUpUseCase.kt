package com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase

import com.google.firebase.auth.AuthResult
import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import com.wantech.gdsc_msu.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        userName: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return authRepository.signUpWithEmailAndPassword(email.trim(), password.trim())
    }
}