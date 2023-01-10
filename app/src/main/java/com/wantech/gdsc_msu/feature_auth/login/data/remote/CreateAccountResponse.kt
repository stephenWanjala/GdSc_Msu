package com.wantech.gdsc_msu.feature_auth.login.data.remote

data class CreateAccountResponse(
    val email: String,
    val displayName: String,
    val password: String
)
