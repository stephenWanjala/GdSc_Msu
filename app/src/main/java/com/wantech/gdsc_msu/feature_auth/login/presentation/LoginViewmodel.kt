package com.wantech.gdsc_msu.feature_auth.login.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import com.wantech.gdsc_msu.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val authRepository: AuthRepository
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
                loginUser(email = _state.value.email, password = _state.value.password)
            }
            LoginUiEvent.ToForgotPassword -> {

            }
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signInWithEmailAndPassword(email, password).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        _loginUIState.emit(
                            LoginState(login = result.data)
                        )
                        _state.value = state.value.copy(
                            email = "", password = ""
                        )
                    }
                    is Resource.Error -> {
                        _loginUIState.emit(
                            LoginState(error = result.uiText)
                        )
                        _state.value = state.value.copy(
                            email = "", password = ""
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

    val isCurrentUserExist: Flow<Boolean>
        get() {
            return authRepository.isCurrentUserExist()
        }

    fun getUserEmail(): String {
        var email: String by mutableStateOf("")
        viewModelScope.launch {
            authRepository.getCurrentUserEmail().collect {
                email = it
            }
        }
        return email
    }



fun signOut() {
    viewModelScope.launch(Dispatchers.IO) {
        authRepository.signOut()
    }
}
}