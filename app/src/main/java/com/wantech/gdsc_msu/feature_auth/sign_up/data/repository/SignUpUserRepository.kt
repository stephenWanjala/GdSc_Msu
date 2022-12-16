package com.wantech.gdsc_msu.feature_auth.sign_up.data.repository

import com.wantech.gdsc_msu.feature_auth.login.data.remote.CreateAccountResponse
import com.wantech.gdsc_msu.util.Resource
import kotlinx.coroutines.flow.Flow

interface SignUpUserRepository {
    suspend fun register(
        email: String,
        username: String,
        password: String
    ): Flow<Resource<CreateAccountResponse>>


}