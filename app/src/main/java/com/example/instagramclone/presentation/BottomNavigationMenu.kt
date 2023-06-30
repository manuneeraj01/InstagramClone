package com.example.instagramclone.presentation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.instagramclone.util.Screens

enum class BottomNavigationMenu(val icon:ImageVector, val route:Screens){
    FEEDS(Icons.Default.Home, Screens.FeedsScreen),
    SEARCH(Icons.Default.Search, Screens.)
}
@Composable
fun BottomNavigationMenu(
    selectedItem:BottomNavigationItem, navController: NavController
) {
}