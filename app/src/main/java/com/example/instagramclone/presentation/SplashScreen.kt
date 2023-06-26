package com.example.instagramclone.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SplashScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = {}){
        Image(painter = painterResource(id = ), contentDescription = )
    }
}