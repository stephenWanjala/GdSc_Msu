package com.wantech.gdsc_msu.feature_auth.sign_up.presentation

sealed class SignupEvent{
    data class EnteredUsername(val value: String): SignupEvent()
    data class EnteredEmail(val value: String): SignupEvent()
    data class EnteredPassword(val value: String): SignupEvent()
    object TogglePasswordVisibility : SignupEvent()
    object ToSignInScreen : SignupEvent()
    object Signup : SignupEvent()
}
