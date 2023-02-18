package com.kjk.lorempicsumapp.ui.presentation.navigation

import androidx.compose.runtime.Composable

sealed class LoremPictureDestinations(
    val route: String
) {
    object Home : LoremPictureDestinations("home")
    object Detail : LoremPictureDestinations("detail")
}

@Composable
fun MainNavHost() {
    
}