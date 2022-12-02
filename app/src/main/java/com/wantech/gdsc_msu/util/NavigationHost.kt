package com.wantech.gdsc_msu.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.LoginScreen
import com.wantech.gdsc_msu.feature_auth.sign_up.presentation.componets.SignUpScreen
import com.wantech.gdsc_msu.feature_main.events.presentation.components.EventsScreen
import com.wantech.gdsc_msu.feature_main.learnResources.presentation.components.LearningResourcesScreen
import com.wantech.gdsc_msu.feature_main.news.presentation.components.NewsUpdatesScreen
import com.wantech.gdsc_msu.feature_main.presentation.BottomNavItem
import com.wantech.gdsc_msu.feature_main.presentation.MainHomeScreen
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileScreen

@Composable
fun NavigationHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoginAccountScreen.route,
        
    ){
        composable(Screen.LoginAccountScreen.route){
            LoginScreen(navController = navHostController)
        }
        composable(Screen.SignUpAccount.route){
            SignUpScreen(NavController = navHostController)
        }
        activity(route = "mainHome"){
            this.activityClass =MainHomeScreen::class
        }


    }
    
}