package com.wantech.gdsc_msu.feature_main.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import com.wantech.gdsc_msu.util.Screen

sealed class BottomNavItem(
    val icon: ImageVector,
    val destinationRoute: String,
    val title: String
) {
    object Events : BottomNavItem(
        icon = Icons.Default.Home,
        destinationRoute = Screen.Events.route,
        title = "Events"
    )

    object Profile : BottomNavItem(
        icon = Icons.Default.Person,
        destinationRoute = Screen.Profile.route,
        title = "Profile"
    )

    object News : BottomNavItem(
        icon = Icons.Default.Notifications,
        destinationRoute = Screen.NewsUpdates.route,
        title = "News"
    )

    object LearnResource : BottomNavItem(
        icon = Icons.Default.School,
        destinationRoute = Screen.MoreLearnResources.route,
        title = "Resources"
    )

}

