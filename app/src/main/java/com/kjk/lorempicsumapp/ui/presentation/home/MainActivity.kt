package com.kjk.lorempicsumapp.ui.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kjk.lorempicsumapp.R
import com.kjk.lorempicsumapp.ui.theme.LoremPicsumAppTheme
import com.kjk.lorempicsumapp.ui.theme.navigation.MainNavHost
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
    // TODO 질문 :: navController는 같은 instance인가?
    //  동작원리..? navHost를 정의하면 미리 navigation 그래프를 만들고, navigate할때마다 recomposition?
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: "home"

    Scaffold(
        topBar = {
            LoremPictureAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            // TODO 중첩 그래프 사용 아직은 무리인가? Nested Compose Navigation vs Fragment Navigation
            //  특정 화면에 따라, topBar 혹은 bottomBar를 숨겨야 할때 (여러 진입점이 있을 수 있음)
        }
    ) {
        MainNavHost(navController = navController)
    }
}