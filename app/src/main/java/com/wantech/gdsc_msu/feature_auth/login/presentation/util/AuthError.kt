package com.wantech.gdsc_msu.feature_auth.login.presentation.util


sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail: AuthError()
    object InvalidPassword : AuthError()
}