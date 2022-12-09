package com.wantech.gdsc_msu.feature_auth.login.presentation

sealed class LoginUiEvent{
    data class EnteredEmail(val value: String): LoginUiEvent()
    data class EnteredPassword(val value: String): LoginUiEvent()
    object TogglePasswordVisibility : LoginUiEvent()
    object ToSignUpScreen : LoginUiEvent()
    object Login : LoginUiEvent()
    object  ToForgotPassword:LoginUiEvent()
}
