package com.wantech.gdsc_msu.feature_auth.sign_up.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.gdsc_msu.R
import com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase.SignUpUseCase
import com.wantech.gdsc_msu.util.Resource
import com.wantech.gdsc_msu.util.UiEvent
import com.wantech.gdsc_msu.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
                register()
            }

            is SignupEvent.ToSignInScreen -> {
            }
        }


    }

    private fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            val registerResult = signUpUseCase(
                email = state.value.email,
                userName = state.value.userName,
                password = state.value.password
            )
            registerResult.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                        )
                    }
                    is Resource.Success -> {
                        UiEvent.ShowSnackbar(UiText.StringResource(R.string.success_registration))
                    }
                }
            }
        }
    }

}