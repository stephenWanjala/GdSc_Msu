package com.wantech.gdsc_msu.feature_auth.sign_up.presentation.componets

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.AButton
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.InputTextField
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.LogoSection
import com.wantech.gdsc_msu.feature_auth.login.presentation.componets.PasswordTextField
import com.wantech.gdsc_msu.feature_auth.sign_up.presentation.SignUpViewModel
import com.wantech.gdsc_msu.feature_auth.sign_up.presentation.SignupEvent
import com.wantech.gdsc_msu.ui.theme.SurfaceVariantDark
import com.wantech.gdsc_msu.ui.theme.SurfaceVariantLight
import com.wantech.gdsc_msu.util.Screen


@Composable
fun SignUpSection(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state = viewModel.state.value
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
                    .padding(horizontal = 12.dp, vertical = 32.dp)
                    .background(
                        color = if (isSystemInDarkTheme()) SurfaceVariantDark else SurfaceVariantLight,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentColor = MaterialTheme.colors.onBackground,
                backgroundColor =
                if (isSystemInDarkTheme()) SurfaceVariantDark else SurfaceVariantLight,
                shape = RoundedCornerShape(12.dp),
                elevation = 0.dp
            ) {
                LoginTextInputFields(onClickLoginButton = {
                    viewModel.onEvent(SignupEvent.Signup)
                    navController.clearBackStack(Screen.SignUpAccount.route)
                    navController.navigate(Screen.LoginAccountScreen.route) {
                        popUpTo(Screen.LoginAccountScreen.route) {
                            inclusive = true
                        }
                    }

                },
                    onClickToSignUp = {
                        navController
                            .clearBackStack(route = Screen.SignUpAccount.route)

                        navController
                            .navigate(Screen.MainHome.route) {
                                popUpTo(Screen.MainHome.route) {
                                    inclusive = true
                                }
                            }
                    }
                )
            }


        }
    }
}


@Composable
fun LoginTextInputFields(
    onClickLoginButton: () -> Unit, onClickToSignUp: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var emailFieldState by remember {
        mutableStateOf("")
    }
    var userNameFieldState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
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
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    InputTextField(
                        textValue = state.userName,
                        labelText = "UserName",
                        onValueChange = { viewModel.onEvent(SignupEvent.EnteredUsername(it)) },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )

                    InputTextField(
                        textValue = state.email,
                        labelText = "Email",
                        onValueChange = { viewModel.onEvent(SignupEvent.EnteredEmail(it)) },
                        modifier = Modifier.weight(0.5f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )

                    )
                }
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(),
                    textValue = state.password,
                    labelText = "Password",

                    placeHolder = "Your Password",
                    onValueChange = {
                        viewModel.onEvent(SignupEvent.EnteredPassword(it))

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    passwordModifier = Modifier.fillMaxWidth(0.9f)

                )


                AButton(text = "Sign Up",
                    onClick = onClickToSignUp,
                    modifier = Modifier.fillMaxWidth(0.7f),
                    buttonEnabled = {
                        state.email.isNotBlank() && state.password.isNotBlank() &&
                                state.userName.isNotBlank() && state.password.length > 6 && state.userName.length > 3 &&
                                state.password.isNotBlank() && ((state.password.length >= 8) && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                            state.email
                        ).matches())
                    }

                )

                TextButton(
                    onClick = onClickLoginButton,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = "Already Have an Account?",
                        color = MaterialTheme.colors.surface,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign In",
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(4.dp),
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
                    text = "Create Account",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                InputTextField(
                    textValue = state.userName,
                    labelText = "UserName",
                    onValueChange = { viewModel.onEvent(SignupEvent.EnteredUsername(it)) },
                )

                InputTextField(
                    textValue = state.email,
                    labelText = "Email",
                    onValueChange = { viewModel.onEvent(SignupEvent.EnteredEmail(it)) },
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
                        viewModel.onEvent(SignupEvent.EnteredPassword(it))

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                )

                AButton(text = "Sign Up",
                    onClick = onClickToSignUp,
                    modifier = Modifier.wrapContentSize(),
                    buttonEnabled = {
                        state.email.isNotBlank() && state.password.isNotBlank() &&
                                state.userName.isNotBlank() && state.password.length > 6
                                && state.userName.length > 3 &&
                                state.password.isNotBlank() && ((state.password.length >= 8)
                                && state.email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                            state.email
                        ).matches())
                    }

                )

                TextButton(
                    onClick = onClickLoginButton,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = "Already Have Account?",
                        color = MaterialTheme.colors.surface,

                        )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign In",
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
        }

    }

}





