package com.kjk.lorempicsumapp.ui.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kjk.lorempicsumapp.R
import com.kjk.lorempicsumapp.ui.presentation.detail.DetailScreen
import com.kjk.lorempicsumapp.ui.presentation.detail.DetailViewModel
import com.kjk.lorempicsumapp.ui.theme.LoremPicsumAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoremPicsumAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoremPictureApp()
                }
            }
        }
    }
}

@Composable
fun LoremPictureAppBar(
    modifier: Modifier = Modifier,
    currentScreen: String,
    canNavigateBack: Boolean = true,
    navigateUp: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = currentScreen
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = {
                        navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LoremPictureApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = backStackEntry?.destination?.route ?: "home"

    Scaffold(
        topBar = {
            LoremPictureAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = modifier.padding(innerPaddings)
        ) {

            composable(route = "home") {
                val viewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(
                    viewModel = viewModel,
                    navigateToDetail = { pictureId ->
                        navController.navigate("detail/$pictureId")
                    }
                )
            }

            composable(
                route = "detail/{pictureId}",
                arguments = listOf(
                    navArgument("pictureId") { type = NavType.StringType }
                )
            ) {
                val viewModel = hiltViewModel<DetailViewModel>()
                DetailScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}