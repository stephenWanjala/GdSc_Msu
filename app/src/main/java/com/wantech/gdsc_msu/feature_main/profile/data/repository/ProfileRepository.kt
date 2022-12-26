package com.wantech.gdsc_msu.feature_main.profile.data.repository

interface ProfileRepository{
    suspend fun logOut()
    val switchTheme: Boolean
}