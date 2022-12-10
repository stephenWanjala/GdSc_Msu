package com.wantech.gdsc_msu.feature_auth.login.data.remote

data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)