package com.wantech.gdsc_msu.feature_main.profile.presentation.components

import com.wantech.gdsc_msu.core.domain.model.Profile


data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val isLogoutDialogVisible: Boolean = false
)