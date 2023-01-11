package com.wantech.gdsc_msu.feature_main.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.gdsc_msu.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel(){
    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            userDataRepository.setTheme(themeValue = themeValue)
        }
    }
}