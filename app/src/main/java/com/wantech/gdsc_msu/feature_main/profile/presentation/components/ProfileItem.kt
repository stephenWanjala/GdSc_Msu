package com.wantech.gdsc_msu.feature_main.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wantech.gdsc_msu.feature_main.profile.domain.model.ProfileItemModel

@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    profileItemModel: ProfileItemModel,
    onclickProfileItem: (ProfileItemModel) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
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

        Icon(
            imageVector = Icons.Default.ArrowForwardIos, contentDescription = null,
//            tint = MaterialTheme.colors.secondary,
            modifier = Modifier.size(10.dp)
        )
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