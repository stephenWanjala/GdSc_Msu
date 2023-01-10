package com.wantech.gdsc_msu.feature_auth.login.presentation

sealed class LoginEvent{
    data class EnteredEmail(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object ToSignUpScreen : LoginEvent()
    object Login : LoginEvent()
    object  ToForgotPassword:LoginEvent()
}
