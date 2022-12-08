package com.wantech.gdsc_msu.feature_auth.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(LoginUiState())
    val state: State<LoginUiState> = _state

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EnteredEmail -> {
                _state.value = _state.value.copy(email = event.value)
            }
            is LoginUiEvent.EnteredPassword -> {
                _state.value = _state.value.copy(password = event.value)
            }
            is LoginUiEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }
            is LoginUiEvent.ToSignUpScreen -> {
            }
            is LoginUiEvent.Login -> {
            }
        }
    }
}