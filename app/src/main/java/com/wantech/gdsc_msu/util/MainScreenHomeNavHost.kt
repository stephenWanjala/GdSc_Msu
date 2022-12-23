package com.wantech.gdsc_msu.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.gdsc_msu.feature_main.events.presentation.components.EventsScreen
import com.wantech.gdsc_msu.feature_main.learnResources.presentation.components.LearningResourcesScreen
import com.wantech.gdsc_msu.feature_main.news.presentation.components.NewsUpdatesScreen
import com.wantech.gdsc_msu.feature_main.profile.presentation.ProfileScreen

@Composable
fun MainHomeScreenNavHost(navController:NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Events.route){
        composable(Screen.Events.route){
            EventsScreen()
        }
        composable(Screen.MoreLearnResources.route){
            LearningResourcesScreen()
        }
        composable(Screen.Profile.route){
            ProfileScreen()
        }
        composable(Screen.NewsUpdates.route){
            NewsUpdatesScreen()
        }
    }
}