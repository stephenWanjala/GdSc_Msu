package com.wantech.gdsc_msu.feature_auth.login.presentation.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun AButton(
    text: String, onClick: () -> Unit, modifier: Modifier, buttonEnabled: () -> Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(start = 32.dp, end = 32.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        enabled = buttonEnabled()
    ) {
        Text(
            text = text, style = MaterialTheme.typography.body2
        )

    }
}