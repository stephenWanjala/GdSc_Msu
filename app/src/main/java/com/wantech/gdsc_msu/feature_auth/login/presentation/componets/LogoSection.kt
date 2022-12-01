package com.wantech.gdsc_msu.feature_auth.login.presentation.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wantech.gdsc_msu.R

@Composable
fun LogoSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google_developers_logo),
                contentDescription = "logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = stringResource(R.string.club_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )

            Text(
                text = stringResource(R.string.university_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )

        }


    }
}