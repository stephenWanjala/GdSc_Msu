package com.wantech.gdsc_msu.feature_main.profile.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.gdsc_msu.core.data.repository.UserDataRepository
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileState
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileToolbarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel(){

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private val _toolbarState = mutableStateOf(ProfileToolbarState())
    val toolbarState: State<ProfileToolbarState> = _toolbarState

    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            userDataRepository.setTheme(themeValue = themeValue)
        }
    }


    fun setExpandedRatio(ratio: Float) {
        _toolbarState.value = _toolbarState.value.copy(expandedRatio = ratio)
    }

    fun setToolbarOffsetY(value: Float) {
        _toolbarState.value = _toolbarState.value.copy(toolbarOffsetY = value)
    }
}