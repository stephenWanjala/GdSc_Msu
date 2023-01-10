package com.wantech.gdsc_msu.feature_auth.login.domain.model

import com.wantech.gdsc_msu.feature_auth.login.presentation.util.AuthError
import com.wantech.gdsc_msu.core.util.SimpleResource

data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)