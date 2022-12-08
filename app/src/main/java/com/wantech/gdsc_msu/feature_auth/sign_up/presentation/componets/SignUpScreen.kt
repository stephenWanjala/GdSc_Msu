package com.wantech.gdsc_msu.feature_auth.sign_up.presentation.componets

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wantech.gdsc_msu.feature_auth.sign_up.presentation.SignUpViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SignUpScreen(NavController: NavHostController) {

    SignUpSection(navController = NavController)
}