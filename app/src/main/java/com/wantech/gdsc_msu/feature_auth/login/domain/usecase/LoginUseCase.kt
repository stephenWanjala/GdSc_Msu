package com.wantech.gdsc_msu.feature_auth.login.domain.usecase

import com.wantech.gdsc_msu.feature_auth.login.data.repository.LoginUserRepository

class LoginUseCase(
    private val loginUserRepository: LoginUserRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return loginUserRepository.loginUser(email, password)
    }
}
