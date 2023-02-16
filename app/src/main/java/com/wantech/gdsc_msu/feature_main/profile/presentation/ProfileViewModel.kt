package com.wantech.gdsc_msu.feature_main.profile.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.gdsc_msu.core.data.repository.UserDataRepository
import com.wantech.gdsc_msu.feature_main.profile.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val profileRepository: ProfileRepository
) : ViewModel(){
    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            userDataRepository.setTheme(themeValue = themeValue)
        }
    }

    fun updateProfilePicture(uri: Uri) {
        viewModelScope.launch {
            profileRepository.saveProfilePicture(uri = uri)
        }
    }

    fun getProfilePicture() = profileRepository.getProfilePicture()


}