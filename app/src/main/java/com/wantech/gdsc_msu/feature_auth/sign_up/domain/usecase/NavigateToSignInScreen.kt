package com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase

import com.wantech.gdsc_msu.feature_auth.sign_up.data.repository.SignUpUserRepository

class NavigateToSignInScreen (
    private val navigateToSignInScreen:() -> Unit
) {
    suspend operator fun invoke() = navigateToSignInScreen()
}