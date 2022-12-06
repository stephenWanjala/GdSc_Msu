package com.wantech.gdsc_msu.feature_auth.login.data.repository

interface LoginUserRepository {
    suspend fun loginUser(email: String, password: String): Boolean

}