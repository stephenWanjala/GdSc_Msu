package com.wantech.gdsc_msu.feature_auth.login.presentation.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(onNavigate: (String) -> Unit, onNavigateToSignUpScreen: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
//                .background(if (isSystemInDarkTheme()) SurfaceVariantDark else SurfaceVariantLight,),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {
            LoginSection(
                onNavigate = onNavigate,
                onNavigateToSignUpScreen = onNavigateToSignUpScreen
            )
        }
    }
}