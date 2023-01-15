package com.wantech.gdsc_msu.feature_auth.login.presentation.componets

import android.app.Application
import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wantech.gdsc_msu.R
import com.wantech.gdsc_msu.core.presentation.LoadingDialog
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginState
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginUiEvent
import com.wantech.gdsc_msu.feature_auth.login.presentation.LoginViewModel
import com.wantech.gdsc_msu.ui.theme.SurfaceVariantDark
import com.wantech.gdsc_msu.ui.theme.SurfaceVariantLight
import com.wantech.gdsc_msu.util.Screen
import com.wantech.gdsc_msu.util.asString


@Composable
fun LoginSection(
    onNavigate: () -> Unit,
    viewModel: LoginViewModel,
    onNavigateToSignUpScreen: (String) -> Unit,
    application: Application = LocalContext.current.applicationContext as Application

) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val loginState = viewModel.loginUpIState.collectAsState(LoginState())

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        val unUsedPadding = it.calculateTopPadding()
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
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        LoginTextInputFields(
                            onClickLoginButton = {
                                viewModel.onEvent(LoginUiEvent.Login)

                            },
                            onClickToSignUp = { route ->
                                onNavigateToSignUpScreen(route)
                            },
                            onForgetPassword = {},
                            viewModel = viewModel
                        )
                        if (loginState.value.isLoading) {
                            LoadingDialog()
                        }

                    }
                }


            }
        }
    }


    LaunchedEffect(loginState.value) {
        if (loginState.value.error != null) {
            snackbarHostState.showSnackbar(
                message = loginState.value.error!!.asString(context = application),
                actionLabel = "dismiss"
            )
        }
        if (loginState.value.login != null) {
            snackbarHostState.showSnackbar(
                message = "welcome ${loginState.value.login!!.user?.email}",
                duration = SnackbarDuration.Short
            )
            onNavigate()
        }

    }

}


@Composable
fun LoginTextInputFields(
    onClickLoginButton: () -> Unit,
    onClickToSignUp: (String) -> Unit,
    onForgetPassword: () -> Unit,
    viewModel: LoginViewModel
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
                    text = stringResource(id = R.string.login),
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
                        text = stringResource(id = R.string.forgot_password),
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center
                    )
                }

                AButton(text = stringResource(id = R.string.login),
                    onClick = {
                        onClickLoginButton()

                    },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    buttonEnabled = {

                        state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                            state.email
                        ).matches())
                    }

                )

                TextButton(
                    onClick = { onClickToSignUp(Screen.SignUpAccount.route) },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.dont_Have_account),
                        color = MaterialTheme.colors.surface,

                        )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.sign_up),
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
                    text = stringResource(id = R.string.login),
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
                        text = stringResource(R.string.forgot_password),
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center
                    )
                }

                AButton(text = stringResource(R.string.login),
                    onClick = {
                        onClickLoginButton()
                    },
                    modifier = Modifier.wrapContentSize(),
                    buttonEnabled = {
                        state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                            state.email
                        ).matches())
                    }

                )

                TextButton(
                    onClick = { onClickToSignUp(Screen.SignUpAccount.route) },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = stringResource(R.string.dont_Have_account),
                        color = MaterialTheme.colors.surface,

                        )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.sign_up),
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
        }

    }

}





