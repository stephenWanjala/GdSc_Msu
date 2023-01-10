package com.wantech.gdsc_msu.feature_main.profile.domain.model

import android.net.Uri

data class UserProfile(
    val userName: String? = null,
    val email: String?=null,
    val avatarUrl: Uri?=null
)
