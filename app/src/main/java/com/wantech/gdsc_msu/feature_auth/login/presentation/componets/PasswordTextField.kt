package com.wantech.gdsc_msu.feature_auth.login.presentation.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    labelText: String,
    maxLines: Int = 1,
    placeHolder: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password,

        ),
    passwordModifier: Modifier = Modifier,
    onSendAction: (() -> Unit?)? = null

) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        val keyBoardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
//            value = textValue.take(if (textValue.length >= 10) 10 else textValue.length),
            value = textValue,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            label = { Text(text = labelText) },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "passwordVisibility Icon"
                    )
                }
            },

            placeholder = {
                Text(text = placeHolder)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyBoardController?.hide()
                },
                onSend = {
                    keyBoardController?.hide()
                    onSendAction?.let { it() }
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = MaterialTheme.colors.background,
                unfocusedLabelColor = MaterialTheme.colors.background,
                placeholderColor = MaterialTheme.colors.background,

                ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(
                '*'
            ),
            maxLines = maxLines,
            shape = RoundedCornerShape(10.dp),
            modifier = passwordModifier

        )

    }

}
