package com.wantech.gdsc_msu.feature_auth.sign_up.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase.SignUpUseCase
import com.wantech.gdsc_msu.util.Resource
import com.wantech.gdsc_msu.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SignUpUIState())
    val state: State<SignUpUIState> = _state
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _onRegister = MutableSharedFlow<Unit>(replay = 1)
    val onRegister = _onRegister.asSharedFlow()
    private val _SignUpUIState = MutableSharedFlow<SignUpState>()
    val signUpIState = _SignUpUIState.asSharedFlow()

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
                signUp(
                    email = _state.value.email,
                    password = _state.value.password,
                    userName = _state.value.userName
                )
            }

            is SignupEvent.ToSignInScreen -> {
            }
        }


    }

    private fun signUp(userName: String, email: String, password: String) =
        viewModelScope.launch {
            signUpUseCase(userName, email, password).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _SignUpUIState.emit(
                            SignUpState(
                                signUp = result.data
                            )
                        )
                    }
                    is Resource.Error -> {
                        _SignUpUIState.emit(SignUpState(error = result.uiText.toString()))
                    }
                    is Resource.Loading -> {
                        _SignUpUIState.emit(SignUpState(isLoading = true))
                    }
                }
            }.launchIn(viewModelScope)
        }

}