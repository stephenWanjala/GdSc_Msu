package com.wantech.gdsc_msu.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.LoginScreen

@Composable
fun NavigationHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoginAccountScreen.route,
        
    ){
        composable(Screen.LoginAccountScreen.route){
            LoginScreen(navController = navHostController)
        }
    }
    
}