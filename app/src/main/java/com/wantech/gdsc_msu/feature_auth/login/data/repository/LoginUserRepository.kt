package com.wantech.gdsc_msu.feature_auth.login.data.repository

import com.wantech.gdsc_msu.feature_auth.login.data.remote.CreateAccountResponse
import com.wantech.gdsc_msu.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginUserRepository {
    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<CreateAccountResponse>>

}