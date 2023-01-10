package com.wantech.gdsc_msu.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.LoginScreen
import com.wantech.gdsc_msu.feature_auth.sign_up.presentation.componets.SignUpScreen
import com.wantech.gdsc_msu.feature_main.presentation.MainHomeScreen

@Composable
fun NavigationHost(navHostController: NavHostController, onNavigate: () -> Unit) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoginAccountScreen.route,

        ) {
        composable(Screen.LoginAccountScreen.route) {
            LoginScreen(
                onNavigate = { onNavigate() },
                onNavigateToSignUpScreen = { route ->
                    navHostController.navigate(route) {
                        popUpTo(Screen.LoginAccountScreen.route) {
                            inclusive = true
                        }
                    }
                },

                )

        }
        composable(Screen.SignUpAccount.route) {

            SignUpScreen(
                onNavigateToLogin = { route ->
                    navHostController.navigate(route) {
                        popUpTo(Screen.SignUpAccount.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigate = {
                    onNavigate()
                },
                popBackStack = {
                    navHostController.popBackStack(Screen.SignUpAccount.route, true)
                }
            )

        }
//        activity(route = "mainHome") {
//            this.activityClass = MainHomeScreen::class
//        }
    }

}