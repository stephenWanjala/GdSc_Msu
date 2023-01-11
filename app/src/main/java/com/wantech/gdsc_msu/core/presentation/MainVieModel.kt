package com.wantech.gdsc_msu.core.presentation

import androidx.lifecycle.ViewModel
import com.wantech.gdsc_msu.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVieModel @Inject constructor(userDataRepository: UserDataRepository) : ViewModel() {
    val theme = userDataRepository.themeStream
}