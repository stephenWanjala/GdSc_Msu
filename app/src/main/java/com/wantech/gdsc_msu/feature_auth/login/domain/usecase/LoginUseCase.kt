package com.wantech.gdsc_msu.feature_auth.login.domain.usecase

import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signInWithEmailAndPassword(email.trim(), password.trim())
}
