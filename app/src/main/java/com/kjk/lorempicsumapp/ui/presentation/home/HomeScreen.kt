package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kjk.lorempicsumapp.domain.entity.PictureVO
import com.kjk.lorempicsumapp.ui.theme.LoremPicsumAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    LaunchedEffect(key1 = Unit) {
        homeViewModel.homeUiState.collectLatest { event ->
            Timber.d("HomeUiState : ${event}")
            when(event) {
                is HomeUiState.Idle -> {
                    homeViewModel.onEvent(HomeEvent.FetchPictures)
                }



            }
        }
    }
}

@Composable
fun LoremPicsumList(pictureList: List<PictureVO>) {
    LazyColumn {
        items(pictureList) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoremPicsumAppTheme {
        HomeScreen()
    }
}