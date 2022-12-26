package com.wantech.gdsc_msu.feature_main.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wantech.gdsc_msu.feature_main.profile.domain.model.UserProfile
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileHeader

@Composable
fun ProfileScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            val unUsedPadding = it.calculateTopPadding()
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileHeader(
                    userProfile = UserProfile(email = "stephenwanjala145@gmail.com")
                )
            }
        }
    }
}