package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: LoremPictureViewModel = hiltViewModel(),
    navigateToDetail: () -> Unit = {}
) {

    val loremPictureList = viewModel.loremPictureList.collectAsState()

    // Loading > fetch > Loading Complete
    LaunchedEffect(key1 = Unit) {
        viewModel.homeUiState.collectLatest { event ->
            Timber.d("HomeUiState : ${event}")
            when (event) {
                is HomeUiState.Idle -> {
                    viewModel.onEvent(HomeEvent.FetchPictures)
                }

                is HomeUiState.FetchPicturesComplete -> {
                    // 리스트를 화면에 그려줘야 한다.
                }
            }
        }
    }

    if (loremPictureList.value.isNotEmpty()) {
        LoremPictureList(
            pictureList = loremPictureList.value,
            viewModel = viewModel,
            navigateToDetail = navigateToDetail,
        )
    } else {
        Timber.d("list is empty")
    }
}

@Composable
fun LoremPictureList(
    modifier: Modifier = Modifier,
    pictureList: List<LoremPicture>,
    viewModel: LoremPictureViewModel = hiltViewModel(),
    navigateToDetail: () -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pictureList) { loremPicture ->
            LoremPictureItem(
                loremPicture = loremPicture,
                viewModel = viewModel,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoremPictureItem(
    modifier: Modifier = Modifier,
    loremPicture:LoremPicture,
    viewModel: LoremPictureViewModel, // TODO 여기 부분에서 hiltViewModel()을 사용하면, 왜 서로 다른 instance가 생성되는 것일까?
    navigateToDetail: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {
                Timber.d("click Event :: ${loremPicture.id}")
                viewModel.setLoremPictureId(loremPicture.id)
                navigateToDetail()
            }
    ) {
        Card {
            Column {
                GlideImage(
                    model = loremPicture.downloadUrl,
                    contentDescription = null
                )
            }
        }
    }
}