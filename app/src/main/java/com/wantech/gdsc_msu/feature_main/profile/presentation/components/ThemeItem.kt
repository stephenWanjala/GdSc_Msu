package com.wantech.gdsc_msu.feature_main.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemeItem(
    themeName: String, themeValue: Int, icon: ImageVector, onSelectTheme: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        onClick = {
            onSelectTheme(themeValue)
        },
//        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.surface)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Text(
                modifier = Modifier.padding(12.dp),
                text = themeName,
                style = MaterialTheme.typography.caption
            )
        }
    }
}