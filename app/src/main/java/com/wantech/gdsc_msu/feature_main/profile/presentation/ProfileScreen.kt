package com.wantech.gdsc_msu.feature_main.profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wantech.gdsc_msu.R
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.AButton
import com.wantech.gdsc_msu.feature_main.profile.domain.model.ProfileItemModel
import com.wantech.gdsc_msu.feature_main.profile.domain.model.UserProfile
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileHeader
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileItem

@Composable
fun ProfileScreen(appVersionName: String) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            val unUsedPadding = it.calculateTopPadding()

            val profileItems = listOf(
                ProfileItemModel(
                    icon = Icons.Default.VerifiedUser,
                    itemName = "profile Info"
                ),
                ProfileItemModel(
                    icon = Icons.Default.AdminPanelSettings,
                    itemName = "Admins"
                ),
                ProfileItemModel(
                    icon = Icons.Default.PostAdd,
                    itemName = "Post"
                ),
                ProfileItemModel(
                    icon = Icons.Default.Person,
                    itemName = "Google Leads"
                ),
                ProfileItemModel(
                    icon = Icons.Default.Info,
                    itemName = "App Version $appVersionName"
                )
            )
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp)) {
                item {
                    ProfileHeader(
                        userProfile = UserProfile(email = "stephenwanjala145@gmail.com")
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.gdsc_maseno),
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(profileItems.size) { index: Int ->
                    ProfileItem(
                        profileItemModel = profileItems[index],
                        onclickProfileItem = { profileItems[index] }
                    )

                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    AButton(
                        text = stringResource(R.string.logout),
                        onClick = { /*TODO*/ },
                        modifier = Modifier,
                        buttonEnabled = { true },
                        trailingIcon = Icons.Default.Logout
                    )
                }
            }
        }
    }
}