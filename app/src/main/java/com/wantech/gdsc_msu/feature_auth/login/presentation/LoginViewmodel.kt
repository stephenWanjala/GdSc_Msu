package com.wantech.gdsc_msu.feature_auth.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.gdsc_msu.feature_auth.login.domain.usecase.LoginUseCase
import com.wantech.gdsc_msu.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = mutableStateOf(LoginUiState())
    val state: State<LoginUiState> = _state
    private val _loginUIState = MutableSharedFlow<LoginState>()
    val loginUpIState = _loginUIState.asSharedFlow()

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
                loginUser(email = _state.value.email, password = _state.value.email)
            }
            LoginUiEvent.ToForgotPassword -> {

            }
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        _loginUIState.emit(
                            LoginState(login = result.data)
                        )
                    }
                    is Resource.Error -> {
                        _loginUIState.emit(
                            LoginState(error = result.uiText)
                        )
                    }
                    is Resource.Loading -> {
                        _loginUIState.emit(
                            LoginState(isLoading = true)
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}