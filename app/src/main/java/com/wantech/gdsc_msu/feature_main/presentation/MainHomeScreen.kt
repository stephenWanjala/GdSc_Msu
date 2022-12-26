package com.wantech.gdsc_msu.feature_main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wantech.gdsc_msu.ui.theme.GdSc_MsuTheme
import com.wantech.gdsc_msu.util.MainHomeScreenNavHost
import com.wantech.gdsc_msu.util.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainHomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            GdSc_MsuTheme() {


                val navController = rememberNavController()
                var showBottomBar by remember { mutableStateOf(true) }
                val newBackStackEntry by navController.currentBackStackEntryAsState()
                val route = newBackStackEntry?.destination?.route
                val scaffoldState = rememberScaffoldState()

                val bottomBarItems: List<BottomNavItem> = listOf(
                    BottomNavItem.Events,
                    BottomNavItem.LearnResource,
                    BottomNavItem.News,
                    BottomNavItem.Profile
                )
                showBottomBar = route in listOf(
                    Screen.Events.route,
                    Screen.MoreLearnResources.route,
                    Screen.Profile.route,
                    Screen.NewsUpdates.route
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            if (showBottomBar) {
                                BottomNavigation(
                                    modifier = Modifier.height(72.dp),
                                    elevation = 8.dp,
                                    backgroundColor = MaterialTheme.colors.onPrimary
                                ) {
                                    val navBackEntry by navController.currentBackStackEntryAsState()
                                    val currentDestination = navBackEntry?.destination

                                    bottomBarItems.forEach { item ->
                                        val selected =
                                            currentDestination?.route?.contains(item.destinationRoute) == true
                                        BottomNavigationItem(
                                            icon = {
                                                Box(
                                                    modifier = Modifier.fillMaxSize(.62F),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    if (selected) {
                                                        Box(
                                                            modifier = Modifier
                                                                .padding(vertical = 4.dp)
                                                                .clip(RoundedCornerShape(100))
                                                                .background(
                                                                    MaterialTheme.colors.primary.copy(
                                                                        alpha = .12F
                                                                    )
                                                                )
                                                                .padding(
                                                                    horizontal = 20.dp,
                                                                    vertical = 6.dp
                                                                ),
                                                            contentAlignment = Alignment.Center
                                                        ) {
                                                            Icon(
                                                                modifier = Modifier,
                                                                imageVector = item.icon,
                                                                tint = MaterialTheme.colors.primary,
                                                                contentDescription = "Nav icon"
                                                            )
                                                        }
                                                    } else {
                                                        Icon(
                                                            imageVector = item.icon,
                                                            tint = MaterialTheme.colors.primary,
                                                            contentDescription = "Icon"
                                                        )
                                                    }
                                                }
                                            },
                                            label = {
                                                Text(
                                                    text = item.title,
                                                    modifier = Modifier.padding(top = 2.dp),
                                                    color = MaterialTheme.colors.primary,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            },
                                            alwaysShowLabel = false,
                                            selectedContentColor = MaterialTheme.colors.primary,
                                            unselectedContentColor = MaterialTheme.colors.primary.copy(
                                                alpha = .24F
                                            ),
                                            selected = currentDestination?.route?.contains(item.destinationRoute) == true,
                                            onClick = {
                                                navController.navigate(item.destinationRoute) {
                                                    navController.graph.startDestinationRoute?.let { route ->
                                                        popUpTo(route) {
                                                            saveState = true
                                                        }
                                                    }
                                                    restoreState = true
                                                    launchSingleTop = true
                                                }
                                            })
                                    }
                                }
                            }
                        }
                    ) {
                        val unUsedPadding = it.calculateTopPadding()
                        MainHomeScreenNavHost(navController = navController)


                    }
                }
            }
        }
    }
}