package com.wantech.gdsc_msu.util

sealed class Screen(val route:String){
    object LoginAccountScreen:Screen(route = "login")
    object SignUpAccount :Screen(route = "signUp")
    object ForgotAccountPasswordScreen :Screen(route = "forgotPassword")
}
