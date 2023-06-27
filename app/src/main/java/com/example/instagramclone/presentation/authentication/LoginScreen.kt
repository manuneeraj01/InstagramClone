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
import androidx.navigation.NavController
import com.example.instagramclone.R
import com.example.instagramclone.presentation.Toast
import com.example.instagramclone.util.Response
import com.example.instagramclone.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: AuthenticationViewModel) {
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
            Image(
                painter = painterResource(id = R.drawable.instagram_logo),
                contentDescription = "Login Screen Logo",
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Sign In",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(value = emailState.value, onValueChange = {
                emailState.value = it
            }, modifier = Modifier.padding(8.dp),
                label = {
                    Text(text = "Enter your email")
                })

            OutlinedTextField(
                value = passwordState.value, onValueChange = {
                    passwordState.value = it
                },
                modifier = Modifier.padding(8.dp),
                label = {
                    Text(text = "Enter your password")
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(onClick = {
                viewModel.signIn(
                    email = emailState.value,
                    password = passwordState.value
                )
            }, modifier = Modifier.padding(8.dp)) {
                Text(text = "Sign In")
                when (val response = viewModel.signInState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    is Response.Success -> {
                        if (response.data == true) {
                            navController.navigate(Screens.FeedsScreen.route) {
                                popUpTo(Screens.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast(message = "Sign In Failed.")
                        }
                    }

                    is Response.Error -> {
                        Toast(message = response.message)
                    }

                }
            }
            Text(
                text = "New User? Sign Up",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(route = Screens.SignUpScreen.route) {
                            launchSingleTop = true
                        }
                    })


        }
    }

}