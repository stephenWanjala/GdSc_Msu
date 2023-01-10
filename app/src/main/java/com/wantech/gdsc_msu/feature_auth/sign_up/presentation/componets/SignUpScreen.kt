package com.wantech.gdsc_msu.feature_auth.sign_up.presentation.componets

import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(
    onNavigate: () -> Unit,
    onNavigateToLogin: (String) -> Unit,
    popBackStack: () -> Unit
) {

    SignUpSection(
        onNavigate = { onNavigate() },
        onNavigateToLoginScreen = onNavigateToLogin,
        popBackStack
        = popBackStack
    )
}