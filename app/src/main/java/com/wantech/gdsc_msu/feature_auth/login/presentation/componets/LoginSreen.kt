package com.wantech.gdsc_msu.feature_auth.login.presentation.componets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginViewModel

@Composable
fun LoginScreen(
    onNavigate: () -> Unit,
    onNavigateToSignUpScreen: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val isUserExist = viewModel.isCurrentUserExist.collectAsState(initial = true)
    LaunchedEffect(isUserExist.value) {
        if (isUserExist.value) {
            onNavigate()
        }
    }

    if (!isUserExist.value) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    ),
//                .background(if (isSystemInDarkTheme()) SurfaceVariantDark else SurfaceVariantLight,),
                horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
            ) {
                LoginSection(
                    onNavigate = { onNavigate() },
                    onNavigateToSignUpScreen = onNavigateToSignUpScreen,
                    viewModel = viewModel
                )
            }
        }
    }
}