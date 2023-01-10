package com.wantech.gdsc_msu.feature_auth.login.presentation

import com.google.firebase.auth.AuthResult
import com.wantech.gdsc_msu.core.util.UiText

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isEmailError: EmailError? = null,
    val isPasswordError: PasswordError? = null,
    val isLoginButtonEnabled: Boolean = false,
) {
    sealed class EmailError {
        object FieldEmpty : EmailError()
        object InvalidEmail : EmailError()
    }

    sealed class PasswordError {
        object FieldEmpty : PasswordError()
        object InvalidPassword : PasswordError()
        object InputTooShort : PasswordError()
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val login: AuthResult? = null,
    val error: UiText? = null
)

