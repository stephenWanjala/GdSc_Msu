package com.wantech.gdsc_msu.feature_main.profile.presentation.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wantech.gdsc_msu.R
import com.wantech.gdsc_msu.core.presentation.AButton
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.PasswordTextField
import com.wantech.gdsc_msu.feature_main.profile.domain.model.ProfileItemModel

@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    profileItemModel: ProfileItemModel,
    onclickProfileItem: (ProfileItemModel) -> Unit
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
//            .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    onclickProfileItem(profileItemModel)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    imageVector = profileItemModel.icon,
                    contentDescription = profileItemModel.itemName
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = profileItemModel.itemName,
                    textAlign = TextAlign.Center
                )
            }


        }

    }
}


@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    profileItemModel: ProfileItemModel,
    onclickProfileItem: (ProfileItemModel) -> Unit,
    adminPost: Boolean = false
) {
    var showPasswordInput by remember {
        mutableStateOf(false)

    }

    var password by remember {
        mutableStateOf("")
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onclickProfileItem(profileItemModel)
                    showPasswordInput = !showPasswordInput
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    imageVector = profileItemModel.icon,
                    contentDescription = profileItemModel.itemName
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = profileItemModel.itemName,
                    textAlign = TextAlign.Center
                )
            }


        }
        if (adminPost) {
            AnimatedVisibility(
                visible = showPasswordInput,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PasswordTextField(
                        textValue = password,
                        labelText = "Enter password",
                        placeHolder = "password",
                        onValueChange = { password = it },
                        keyboardOptions = KeyboardOptions().copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        )
                    )
                    AButton(
                        text = stringResource(id = R.string.ok),
                        onClick = { showPasswordInput = !showPasswordInput },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .align(Alignment.End),
                        buttonEnabled = { password.isNotEmpty() && password.length >= 6 })
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
    ProfileItem(profileItemModel = ProfileItemModel(
        icon = Icons.Default.VerifiedUser,
        itemName = "profile Info"
    ), onclickProfileItem = {})
}