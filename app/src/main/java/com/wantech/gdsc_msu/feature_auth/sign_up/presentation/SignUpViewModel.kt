package com.wantech.gdsc_msu.feature_auth.sign_up.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private val _state = mutableStateOf(SignUpUIState())
    val state: State<SignUpUIState> = _state


    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.EnteredUsername -> {
                _state.value = _state.value.copy(userName = event.value)
            }

            is SignupEvent.EnteredEmail -> {
                _state.value = _state.value.copy(email = event.value)
            }

            is SignupEvent.EnteredPassword -> {
                _state.value = _state.value.copy(password = event.value)
            }

            is SignupEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }

            is SignupEvent.Signup -> {
            }

            is SignupEvent.ToSignInScreen -> {
            }

        }
    }

}