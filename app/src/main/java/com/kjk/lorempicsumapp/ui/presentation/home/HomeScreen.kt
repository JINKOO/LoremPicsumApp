package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kjk.lorempicsumapp.R
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.ui.presentation.common.CommonProgressBar
import timber.log.Timber

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val uiEvent by viewModel.homeUiState.collectAsState()
    val pagingList = viewModel.pagingFlow.collectAsLazyPagingItems()

    Timber.d("PagingList :: ${pagingList.loadState}")

    Column {
        if (!uiEvent.isFetchComplete) {
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                onClick = {
                    // TODO 질문 paging을 사용하면, fetch 버튼을 클릭할 때 말고, 바로 flow를 가져옴
                    /*viewModel.fetchPictureList()*/
                }
            ) {
                Text(text = stringResource(R.string.fetch_list_btn_label))
            }
        }

        LoremPictureList(
            navigateToDetail = navigateToDetail,
            pagingList = pagingList
        )

        if (uiEvent.isLoading) {
            CommonProgressBar()
        }
    }
}

@Composable
fun LoremPictureList(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    pagingList: LazyPagingItems<LoremPicture>
) {

    LazyVerticalGrid(
        modifier = modifier
            .padding(8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (val state = pagingList.loadState.prepend) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                state.error.message?.let { ErrorText(message = it) }
            }
        }

        when (val state = pagingList.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                state.error.message?.let { ErrorText(message = it) }
            }
        }
        items(
            count = pagingList.itemCount
        ) { index ->
            val loremPicture = pagingList[index] ?: return@items
            LoremPictureItem(
                loremPicture = loremPicture,
                navigateToDetail = { navigateToDetail(loremPicture.id) }
            )
        }
        when (val state = pagingList.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                state.error.message?.let { ErrorText(message = it) }
            }
        }
    }
}

@Composable
fun LoremPictureItem(
    modifier: Modifier = Modifier,
    loremPicture: LoremPicture,
    navigateToDetail: () -> Unit
) {
    Card(
        modifier = modifier
            .size(200.dp)
            .clickable { navigateToDetail() },
        elevation = 4.dp
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(loremPicture.downloadUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.ic_broken_image),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

private fun LazyGridScope.Loading() {
    item {
        CommonProgressBar()
    }
}

private fun LazyGridScope.ErrorText(
    message: String
) {
    item {
        Text(
            text = message
        )
    }
}