package com.wantech.gdsc_msu.util

sealed class UiEvent: Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    object OnLogin: UiEvent()
}