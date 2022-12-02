package com.wantech.gdsc_msu.feature_main.profile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        val unUsedPadding = it.calculateTopPadding()
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Profile")
        }
    }
}