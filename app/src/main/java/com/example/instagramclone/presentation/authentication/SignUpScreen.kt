package com.example.instagramclone.presentation.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.instagramclone.R
import com.example.instagramclone.presentation.Toast
import com.example.instagramclone.util.Response
import com.example.instagramclone.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController, viewModel: AuthenticationViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val emailState = remember {
                mutableStateOf("")
            }
            val passwordState = remember {
                mutableStateOf("")
            }
            val userNameState = remember {
                mutableStateOf("")
            }
            Image(
                painter = painterResource(id = R.drawable.instagram_logo),
                contentDescription = "Sign up Logo",
                modifier = Modifier
                    .width(250.dp)
                    .padding(16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Sign up",
                modifier = Modifier.padding(8.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(value = emailState.value, onValueChange = {
                emailState.value = it
            }, modifier = Modifier.padding(8.dp), label = { Text(text = "Enter your email") })

            OutlinedTextField(value = passwordState.value, onValueChange = {
                passwordState.value = it
            }, modifier = Modifier.padding(8.dp), label = {
                Text(text = "Enter your password")
            }, visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(value = userNameState.value, onValueChange = {
                userNameState.value = it
            }, modifier = Modifier.padding(8.dp), label = { Text(text = "Enter your User Name") })

            Button(onClick = {
                viewModel.signUp(
                    email = emailState.value,
                    password = passwordState.value,
                    userName = userNameState.value
                )
            }, modifier = Modifier.padding(8.dp)) {
                Text(text = "Sign Up")
                when (val response = viewModel.signUpState.value) {
                    is Response.Success -> {
                        if (response.data == true) {
                            navController.navigate(Screens.FeedsScreen.route) {
                                popUpTo(Screens.SignUpScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast(message = "Sign up Failed")
                        }
                    }

                    is Response.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    is Response.Error -> {
                        Toast(message = response.message)
                    }
                }
            }
            Text(
                text = "Already a User? Sign in", color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screens.LoginScreen.route) {
                            launchSingleTop = true
                        }
                    }
                )
        }
    }

}