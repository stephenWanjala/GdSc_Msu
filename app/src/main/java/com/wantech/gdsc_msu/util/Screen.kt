package com.wantech.gdsc_msu.util

sealed class Screen(val route:String){
    object LoginAccountScreen:Screen(route = "login")
}
