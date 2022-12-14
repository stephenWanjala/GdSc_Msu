package com.wantech.gdsc_msu.feature_auth.login.presentation.componets

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginUiEvent
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginViewModel
import com.wantech.gdsc_msu.ui.theme.SurfaceVariantDark
import com.wantech.gdsc_msu.ui.theme.SurfaceVariantLight
import com.wantech.gdsc_msu.util.Screen


@Composable
fun LoginSection(
    navController: NavHostController,

) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            LogoSection()
            Card(
                modifier = Modifier
//                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 32.dp)
                    .background(
                        color = if (isSystemInDarkTheme()) SurfaceVariantDark else SurfaceVariantLight,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentColor = MaterialTheme.colors.onBackground,
                backgroundColor = if (isSystemInDarkTheme()) SurfaceVariantDark else SurfaceVariantLight,
                shape = RoundedCornerShape(12.dp),
                elevation = 0.dp

            ) {
                LoginTextInputFields(
                    onClickLoginButton = {
                        navController
                            .clearBackStack(route = Screen.LoginAccountScreen.route)
                        navController
                            .navigate(Screen.MainHome.route) {
                                popUpTo(Screen.MainHome.route) {
                                    inclusive = true
                                }
                            }

                    },
                    onClickToSignUp = {
                        navController.clearBackStack(Screen.LoginAccountScreen.route)
                        navController.navigate(Screen.SignUpAccount.route) {
                            popUpTo(Screen.SignUpAccount.route) {
                                inclusive = true
                            }
                        }
                    },
                    onForgetPassword = {})
            }


        }
    }
}


@Composable
fun LoginTextInputFields(
    onClickLoginButton: () -> Unit, onClickToSignUp: () -> Unit, onForgetPassword: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    var orientation by remember {
        mutableStateOf(Configuration.ORIENTATION_PORTRAIT)
    }
    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }.collect { orientation = it }
    }

    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    InputTextField(
                        textValue = state.email,
                        labelText = "Email",
                        onValueChange = { viewModel.onEvent(LoginUiEvent.EnteredEmail(it)) },
                        modifier = Modifier.weight(0.5f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )

                    )


                    PasswordTextField(
                        modifier = Modifier.weight(0.5f),
                        textValue = state.password,
                        labelText = "Password",

                        placeHolder = "Your Password",
                        onValueChange = {
                            viewModel.onEvent(LoginUiEvent.EnteredPassword(it))

                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                        )

                    )

                }

                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = onForgetPassword,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(end = 64.dp)
                        .align(Alignment.End),
                    contentPadding = PaddingValues(1.dp),
                ) {
                    Text(
                        text = "Forgot password?",
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center
                    )
                }

                AButton(text = "Login",
                    onClick = onClickLoginButton,
                    modifier = Modifier.fillMaxWidth(0.7f),
                    buttonEnabled = {

                        state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                            state.email
                        ).matches())
                    }

                )

                TextButton(
                    onClick = { viewModel.onEvent(LoginUiEvent.Login) },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = "Don't Have Account?",
                        color = MaterialTheme.colors.surface,

                        )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign Up",
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }

        }
        else -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
//                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                InputTextField(
                    textValue = state.email,
                    labelText = "Email",
                    onValueChange = { viewModel.onEvent(LoginUiEvent.EnteredEmail(it)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )

                )


                PasswordTextField(
                    textValue = state.password,
                    labelText = "Password",
                    placeHolder = "Your Password",

                    onValueChange = {
                        viewModel.onEvent(LoginUiEvent.EnteredPassword(it))

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),

                    )
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = { viewModel.onEvent(LoginUiEvent.ToForgotPassword) },
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(end = 32.dp)
                        .align(Alignment.End),
                    contentPadding = PaddingValues(1.dp),
                ) {
                    Text(
                        text = "Forgot password?",
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center
                    )
                }

                AButton(text = "Login",
                    onClick = {viewModel.onEvent(LoginUiEvent.Login)},
                    modifier = Modifier.wrapContentSize(),
                    buttonEnabled = {
                        state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                            state.email
                        ).matches())
                    }

                )

                TextButton(
                    onClick = {viewModel.onEvent(LoginUiEvent.ToSignUpScreen)},
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = "Don't Have Account?",
                        color = MaterialTheme.colors.surface,

                        )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign Up",
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
        }

    }

}





