package com.wantech.gdsc_msu.feature_auth.sign_up.presentation

data class SignUpUIState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUserNameError: UsernameError? = null,
    val isEmailError: EmailError? = null,
    val isPasswordError: PasswordError? = null,
    val isSignUpButtonEnabled: Boolean = false,

    ) {
    sealed class UsernameError {
        object FieldEmpty : UsernameError()
        object InputTooShort : UsernameError()
    }
    sealed class EmailError {
        object FieldEmpty : EmailError()
        object InvalidEmail: EmailError()
    }
    sealed class PasswordError {
        object FieldEmpty: PasswordError()
        object InvalidPassword : PasswordError()
        object InputTooShort : PasswordError()
    }

}
