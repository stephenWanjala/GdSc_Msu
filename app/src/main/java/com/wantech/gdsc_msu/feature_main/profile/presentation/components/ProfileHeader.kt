package com.wantech.gdsc_msu.feature_main.profile.presentation.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wantech.gdsc_msu.R
import com.wantech.gdsc_msu.feature_main.profile.domain.model.UserProfile

@Composable
fun ProfileHeader(
    userProfile: UserProfile,
    modifier: Modifier = Modifier,

) {
    var selectedImageUri: Uri? by remember {
        mutableStateOf(null)
    }
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
        }
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {


        IconButton(
            onClick = {
                /*
                *TODO
                * getImageFrom device
                * Upload to db
                * update profile
                 */
                photoPicker.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }, modifier = Modifier
                .clip(CircleShape)
                .background(
                    shape = CircleShape,
                    color = MaterialTheme.colors.background.copy(alpha = 0.4f)
                )
        ) {
            if (selectedImageUri == null) {
                Icon(
                    imageVector = Icons.Rounded.Person, contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(
                            shape = CircleShape,
                            color = MaterialTheme.colors.primary,
                            width = 1.dp
                        )
                )
            }
            /*
            *
            * decode image url to bitmap
             */
            AsyncImage(
                model = selectedImageUri, contentDescription = userProfile.userName,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        shape = CircleShape,
                        color = MaterialTheme.colors.primary,
                        width = 1.dp
                    ),
                placeholder = painterResource(id = R.drawable.ic_image),
                contentScale = ContentScale.Crop
            )

        }

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = userProfile.email ?: "",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )

        }
//        var checked by remember {
//            mutableStateOf(false)
//        }
//
//        /*
//        *
//        * Theme switcher
//         */
//        Switch(
//            checked = checked,
//            onCheckedChange = { checked =!checked },
//            modifier = Modifier,
//
//            )

    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {

}