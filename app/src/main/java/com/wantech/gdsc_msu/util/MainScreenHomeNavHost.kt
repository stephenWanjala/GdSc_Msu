package com.wantech.gdsc_msu.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.wantech.gdsc_msu.core.util.Screen
import com.wantech.gdsc_msu.feature_main.events.presentation.components.EventsScreen
import com.wantech.gdsc_msu.feature_main.learnResources.presentation.components.LearningResourcesScreen
import com.wantech.gdsc_msu.feature_main.news.presentation.components.NewsUpdatesScreen
import com.wantech.gdsc_msu.feature_main.profile.presentation.ProfileScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainHomeScreenNavHost(navController: NavHostController, appVersionName: String) {
    val pagerState = rememberPagerState()
    NavHost(navController = navController, startDestination = Screen.Events.route) {
        composable(Screen.Events.route) {
            EventsScreen()
        }
        composable(Screen.MoreLearnResources.route) {
            LearningResourcesScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen(appVersionName = appVersionName)
        }
        composable(Screen.NewsUpdates.route) {
            NewsUpdatesScreen(pagerState = pagerState)
        }
    }
}