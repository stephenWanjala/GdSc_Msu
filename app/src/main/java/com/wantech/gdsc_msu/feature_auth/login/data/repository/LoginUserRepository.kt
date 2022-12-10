package com.wantech.gdsc_msu.feature_auth.login.data.repository

import com.wantech.gdsc_msu.util.SimpleResource

interface LoginUserRepository {
    suspend fun login(
        email: String,
        password: String
    ): SimpleResource

}