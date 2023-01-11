package com.wantech.gdsc_msu.feature_main.profile.presentation

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.gdsc_msu.AuthActivity
import com.wantech.gdsc_msu.R
import com.wantech.gdsc_msu.core.presentation.AButton
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginViewModel
import com.wantech.gdsc_msu.feature_main.profile.domain.model.ProfileItemModel
import com.wantech.gdsc_msu.feature_main.profile.domain.model.UserProfile
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileHeader
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(appVersionName: String, viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    var orientation = configuration.orientation
    LaunchedEffect(key1 = orientation) {
        snapshotFlow { configuration.orientation }.collectLatest { orientation = it }
    }

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
                    icon = Icons.Default.ShieldMoon,
                    itemName = "Change Your Theme"
                ),
                ProfileItemModel(
                    icon = Icons.Default.Info,
                    itemName = "App Version $appVersionName"
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp),
                horizontalAlignment = if (orientation == Configuration.ORIENTATION_LANDSCAPE) Alignment.CenterHorizontally else Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    ProfileHeader(
                        userProfile = UserProfile(email = viewModel.getUserEmail())
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.gdsc_maseno),
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(profileItems.size) { index: Int ->
                    if (index != (profileItems.lastIndex - 1)) Divider()
                    ProfileItem(
                        profileItemModel = profileItems[index],
                        onclickProfileItem = { profileItem->
                            when (index) {
                                0 -> {

                                }
                                1 -> {

                                }
                                2 -> {

                                }
                                3 -> {

                                }
                                4 -> {

                                }
                                5 -> {

                                }
                            }

                        }
                    )


                }


                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    AButton(
                        text = stringResource(R.string.logout),
                        onClick = {
                            viewModel.signOut()
                            context.startActivity(
                                Intent(
                                    context,
                                    AuthActivity::class.java
                                ).also { intent ->
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                })
                        },
                        modifier = if (orientation == Configuration.ORIENTATION_PORTRAIT) Modifier else Modifier.fillMaxWidth(
                            0.5f
                        ),
                        buttonEnabled = { true },
                        trailingIcon = Icons.Default.Logout
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}