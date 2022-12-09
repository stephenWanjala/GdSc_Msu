package com.wantech.gdsc_msu.feature_auth.login.domain.usecase

import com.wantech.gdsc_msu.feature_auth.login.data.repository.LoginUserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginUserRepository: LoginUserRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return loginUserRepository.loginUser(email, password)
    }
}
