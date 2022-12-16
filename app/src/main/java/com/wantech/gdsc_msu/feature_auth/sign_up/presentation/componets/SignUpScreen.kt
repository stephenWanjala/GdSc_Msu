package com.wantech.gdsc_msu.feature_auth.sign_up.presentation.componets

import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(
    onNavigate: (String) -> Unit,
    onNavigateToLogin: (String) -> Unit
) {

    SignUpSection(
        onNavigate = onNavigate,
        onNavigateToLogin = onNavigateToLogin
    )
}