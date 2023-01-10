package com.wantech.gdsc_msu.core.util

sealed class Screen(val route:String){
    object LoginAccountScreen: Screen(route = "login")
    object SignUpAccount : Screen(route = "signUp")
    object ForgotAccountPasswordScreen : Screen(route = "forgotPassword")
    object MainHome : Screen(route = "mainHome")
    object Events : Screen(route = "events")
    object Profile : Screen(route = "profile")
    object NewsUpdates : Screen(route = "news")
    object MoreLearnResources : Screen(route = "resources")
}
