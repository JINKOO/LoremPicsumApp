package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kjk.lorempicsumapp.ui.theme.LoremPicsumAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail: (LoremPictureVO) -> Unit
) {
    
    var isFetchCompleted by rememberSaveable {
        mutableStateOf(false)
    }
    
    // Loading > fetch > Loading Complete
    LaunchedEffect(key1 = Unit) {
        homeViewModel.homeUiState.collectLatest { event ->
            Timber.d("HomeUiState : ${event}")
            when(event) {
                is HomeUiState.Idle -> {
                    homeViewModel.onEvent(HomeEvent.FetchPictures)
                }

                is HomeUiState.FetchPicturesComplete -> {
                    isFetchCompleted = true
                }
            }
        }
    }
    
    if (isFetchCompleted) {
        LoremPicsumList(
            pictureList = homeViewModel.loremPictureList,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun LoremPicsumList(
    pictureList: List<LoremPictureVO>,
    navigateToDetail: (LoremPictureVO) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pictureList) { loremPicture ->
            LoremPictureItem(
                loremPicture = loremPicture,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoremPictureItem(
    modifier: Modifier = Modifier,
    loremPicture: LoremPictureVO,
    navigateToDetail:(LoremPictureVO) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                Timber.d("click Event :: ${loremPicture}")
                navigateToDetail(loremPicture)
            }
    ) {
        Card() {
            Column {
                GlideImage(
                    model = loremPicture.downloadUrl,
                    contentDescription = null
                )
            }
        }

        Text(
            text = loremPicture.id,
            style = MaterialTheme.typography.h1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoremPicsumAppTheme {
        HomeScreen(navigateToDetail = {})
    }
}