package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.presentation.SplashScreen
import com.example.instagramclone.presentation.authentication.AuthenticationViewModel
import com.example.instagramclone.presentation.authentication.LoginScreen
import com.example.instagramclone.presentation.authentication.SignUpScreen
import com.example.instagramclone.presentation.main.FeedsScreen
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel:AuthenticationViewModel = hiltViewModel()
                    InstagramCloneApp(navController, authViewModel)

                }
            }
        }
    }
}

@Composable
fun InstagramCloneApp(navHostController: NavHostController, authenticationViewModel: AuthenticationViewModel){
    NavHost(navController = navHostController, startDestination = Screens.SplashScreen.route){
        composable(route = Screens.LoginScreen.route){
            LoginScreen(navController = navHostController, viewModel = authenticationViewModel)
        }
        composable(route = Screens.SignUpScreen.route){
            SignUpScreen(navController = navHostController, viewModel = authenticationViewModel)
        }
        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navHostController, authViewModel = authenticationViewModel)
        }
        //Screens after Login
        composable(route = Screens.FeedsScreen.route){
            FeedsScreen()
        }

    }

}