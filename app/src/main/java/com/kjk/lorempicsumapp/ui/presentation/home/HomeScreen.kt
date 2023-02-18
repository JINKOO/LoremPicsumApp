package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kjk.lorempicsumapp.R
import com.kjk.lorempicsumapp.domain.entity.LoremPicture

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val uiEvent by viewModel.homeUiState.collectAsState()
    val pictureList = uiEvent.pictureList

    Column {
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                viewModel.fetchPictureList()
            }
        ) {
            Text(text = stringResource(R.string.fetch_list_btn_label))
        }

        LoremPictureList(
            pictureList = pictureList,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun CommonErrorText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.empty_picture_list_message))
    }
}

@Composable
fun LoremPictureList(
    modifier: Modifier = Modifier,
    pictureList: List<LoremPicture>,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pictureList, key = { picture -> picture.id }) { loremPicture ->
            LoremPictureItem(
                loremPicture = loremPicture,
                navigateToDetail = {
                    navigateToDetail(loremPicture.id)
                }
            )
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