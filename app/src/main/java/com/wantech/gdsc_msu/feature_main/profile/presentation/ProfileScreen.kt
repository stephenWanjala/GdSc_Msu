package com.wantech.gdsc_msu.feature_main.profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.gdsc_msu.core.domain.model.User
import com.wantech.gdsc_msu.core.presentation.theme.ProfilePictureSizeLarge
import com.wantech.gdsc_msu.core.presentation.theme.SpaceSmall
import com.wantech.gdsc_msu.core.util.toPx
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginViewModel
import com.wantech.gdsc_msu.feature_main.profile.domain.model.ProfileItemModel
import com.wantech.gdsc_msu.feature_main.profile.presentation.components.ProfileHeaderSection
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    appVersionName: String, viewModel: LoginViewModel = hiltViewModel(),
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    profileViewModel: ProfileViewModel = hiltViewModel()

) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    var orientation = configuration.orientation
    LaunchedEffect(key1 = orientation) {
        snapshotFlow { configuration.orientation }.collectLatest { orientation = it }
    }
    var shouldShowThemeDialog by remember {
        mutableStateOf(false)
    }


    val iconHorizontalCenterLength =
        (LocalConfiguration.current.screenWidthDp.dp.toPx() / 4f -
                (profilePictureSize / 4f).toPx() -
                SpaceSmall.toPx()) / 2f
    val iconSizeExpanded = 35.dp
    val toolbarHeightCollapsed = 75.dp
    val imageCollapsedOffsetY = remember {
        (toolbarHeightCollapsed - profilePictureSize / 2f) / 2f
    }
    val iconCollapsedOffsetY = remember {
        (toolbarHeightCollapsed - iconSizeExpanded) / 2f
    }
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    val toolbarHeightExpanded = remember {
        bannerHeight + profilePictureSize
    }
    val maxOffset = remember {
        toolbarHeightExpanded - toolbarHeightCollapsed
    }

    val toolbarState = profileViewModel.toolbarState.value
    val state = profileViewModel.state.value
    val lazyListState = rememberLazyListState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val shouldNotScroll = delta > 0f && lazyListState.firstVisibleItemIndex != 0
                if (shouldNotScroll) {
                    return Offset.Zero
                }
                val newOffset = profileViewModel.toolbarState.value.toolbarOffsetY + delta
                profileViewModel.setToolbarOffsetY(
                    newOffset.coerceIn(
                        minimumValue = -maxOffset.toPx(),
                        maximumValue = 0f
                    )
                )
                profileViewModel.setExpandedRatio((profileViewModel.toolbarState.value.toolbarOffsetY + maxOffset.toPx()) / maxOffset.toPx())
                return Offset.Zero
            }
        }
    }
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
            icon = Icons.Default.LightMode,
            itemName = "Change Your Theme"
        ),
        ProfileItemModel(
            icon = Icons.Default.Info,
            itemName = "App Version $appVersionName"
        )
    )
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            val unUsedPadding = it.calculateTopPadding()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = lazyListState
                ) {
                    item {
                        Spacer(
                            modifier = Modifier.height(
                                toolbarHeightExpanded - profilePictureSize / 2f
                            )
                        )
                    }
                    item {
                        if(state.profile!=null){
                            val profile =state.profile
                            ProfileHeaderSection(
                                user = User(
                                    userId = profile.userId,
                                    profilePictureUrl = profile.profilePictureUrl,
                                    username = profile.username,
                                    description = profile.bio,

                                    )
                            )
                        } else{
                            ProfileHeaderSection(
                                user = User(
                                    userId = "userId",
                                    profilePictureUrl = "https://thumbs.dreamstime.com/b/blockchain-line-icon-vector-simple-minimal-pictogram-web-graphics-apps-110060602.jpg",
                                    username = "username",
                                    description = "description,this is a description,kajjxjxxszxsx \n shjdhsjhds xhjsx",

                                    )
                            )
                        }
                    }
                      /*  if (shouldShowThemeDialog) {
                            ThemeDialog(
                                onDismiss = { shouldShowThemeDialog = !shouldShowThemeDialog },
                                onSelectTheme = profileViewModel::updateTheme
                            )
                        }  */
                    }
                }
            }
        }
    }
