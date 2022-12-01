package com.wantech.gdsc_msu.util

sealed class Screen(val route:String){
    object LoginAccountScreen:Screen(route = "login")
    object SignInAccountScreen :Screen(route = "signIn")
    object ForgotAccountPasswordScreen :Screen(route = "forgotPassword")
}
