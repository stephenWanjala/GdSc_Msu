package com.wantech.gdsc_msu.feature_auth.login.domain.usecase

class NavigateToForgotPasswordScreen(
    private val navigateToForgotPasswordScreen: () -> Unit
) {
    operator fun invoke() = navigateToForgotPasswordScreen()
}

