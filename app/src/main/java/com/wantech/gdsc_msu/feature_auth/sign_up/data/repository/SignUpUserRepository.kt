package com.wantech.gdsc_msu.feature_auth.sign_up.data.repository

interface SignUpUserRepository {
    suspend fun signUpUser(userName: String, email: String, password: String)

}