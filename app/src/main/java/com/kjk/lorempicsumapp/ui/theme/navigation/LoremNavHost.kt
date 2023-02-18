package com.kjk.lorempicsumapp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kjk.lorempicsumapp.ui.presentation.detail.DetailScreen
import com.kjk.lorempicsumapp.ui.presentation.detail.DetailViewModel
import com.kjk.lorempicsumapp.ui.presentation.home.HomeScreen
import com.kjk.lorempicsumapp.ui.presentation.home.HomeViewModel

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = LoremPictureDestinations.Home.route
    ) {

        composable(route = LoremPictureDestinations.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                navigateToDetail = { pictureId ->
                    navController.navigateToDetailScreen(pictureId)
                }
            )
        }

        composable(
            route = "${LoremPictureDestinations.Detail.route}/{$PICTURE_ID_ARG}",
            arguments = listOf(
                navArgument(PICTURE_ID_ARG) { type = NavType.StringType }
            )
        ) {
            val viewModel = hiltViewModel<DetailViewModel>()
            DetailScreen(
                viewModel = viewModel
            )
        }
    }
}