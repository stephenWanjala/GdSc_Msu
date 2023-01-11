package com.wantech.gdsc_msu.feature_main.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wantech.gdsc_msu.R
import com.wantech.gdsc_msu.ui.theme.Theme

@Composable
fun ThemeDialog(
    onDismiss: () -> Unit,
    onSelectTheme: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(20.dp),
        title = {
            Text(text = "Themes", style = MaterialTheme.typography.h2)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                ThemeItem(
                    themeName = "Use System Settings",
                    themeValue = Theme.FOLLOW_SYSTEM.themeValue,
                    icon = Icons.Default.SettingsSuggest,
                    onSelectTheme = onSelectTheme
                )

                ThemeItem(
                    themeName = "Light Mode",
                    themeValue = Theme.LIGHT_THEME.themeValue,
                    icon = Icons.Default.LightMode,
                    onSelectTheme = onSelectTheme
                )
                ThemeItem(
                    themeName = "Dark Mode",
                    themeValue = Theme.NIGHT_THEME.themeValue,
                    icon = Icons.Default.ModeNight,
                    onSelectTheme = onSelectTheme
                )
            }
        },
        confirmButton = {
            Text(text = stringResource(R.string.ok),
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() })
        }
    )
}