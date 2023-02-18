package com.kjk.lorempicsumapp.ui.theme.navigation

import androidx.navigation.NavController

const val PICTURE_ID_ARG = "pictureId"

sealed class LoremPictureDestinations(
    val route: String
) {
    object Home : LoremPictureDestinations("home")
    object Detail : LoremPictureDestinations("detail")
}

fun NavController.navigateToHomeScreen() {
    this.navigate(LoremPictureDestinations.Home.route)
}

fun NavController.navigateToDetailScreen(pictureId: String) {
    this.navigate("${LoremPictureDestinations.Detail.route}/$pictureId")
}