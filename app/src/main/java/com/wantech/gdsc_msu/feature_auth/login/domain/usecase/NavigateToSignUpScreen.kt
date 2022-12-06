package com.wantech.gdsc_msu.feature_auth.login.domain.usecase

class NavigateToSignUpScreen (
    private val navigateToSignUpScreen: () -> Unit
) {
    operator fun invoke() = navigateToSignUpScreen()
}
