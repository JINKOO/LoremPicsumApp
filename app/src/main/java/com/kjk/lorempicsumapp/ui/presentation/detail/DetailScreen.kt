package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kjk.lorempicsumapp.R
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import timber.log.Timber

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val detailUiState by viewModel.detailUiState.collectAsState()
    Timber.d("detailUiState :: $detailUiState")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            elevation = 4.dp
        ) {
            ImageThumbNails(pictureUrl = detailUiState.currLoremPicture.downloadUrl)
        }

        PictureInfo(loremPicture = detailUiState.currLoremPicture)
        TwoButtons(
            // TODO 질문 :: 각각 다른 Composable을 호출 할 때, uiState를 넘겨줘도 되나? JetNews등의 코드에서는 허용함.
//            detailUiState = detailUiState,
            onLeftIconClicked = {
                viewModel.event(
                    DetailPictureViewModelEvent.RefreshCurrentPicture(
                        detailUiState.currLoremPicture.id.toInt().minus(1).toString()
                    )
                )
            },
            onRightIconClicked = {
                viewModel.event(
                    DetailPictureViewModelEvent.RefreshCurrentPicture(
                        detailUiState.currLoremPicture.id.toInt().plus(1).toString()
                    )
                )
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ImageThumbNails(
                pictureUrl = detailUiState.prevLoremPicture.downloadUrl,
                width = 70,
                height = 50
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageThumbNails(
                pictureUrl = detailUiState.currLoremPicture.downloadUrl,
                width = 70,
                height = 50
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageThumbNails(
                pictureUrl = detailUiState.nextLoremPicture.downloadUrl,
                width = 70,
                height = 50
            )
        }
    }
}

@Composable
fun ImageThumbNails(
    modifier: Modifier = Modifier,
    pictureUrl: String?,
    height: Int = 300,
    width: Int = 250
) {
    Card(
        modifier = modifier
            .width(width.dp)
            .height(height.dp),
        elevation = 4.dp
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pictureUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.ic_broken_image),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun TwoButtons(
    modifier: Modifier = Modifier,
    onLeftIconClicked: () -> Unit,
    onRightIconClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { onLeftIconClicked() })
            {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_left_24),
                    contentDescription = null
                )
            }
        }
        // 양쪽 정렬 할 수 있는 방법??
        //Spacer(modifier = Modifier.width(250.dp))
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    onRightIconClicked()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_right_24),
                    contentDescription = null
                )
            }
        }
    }
}

// TODO 0번 detail 화면 update
@Composable
private fun PictureInfo(
    loremPicture: LoremPicture
) {
    Row() {
        // 정보 title label 영역
        Column() {
            LoremPictureLabel(label = "ID")
            LoremPictureLabel(label = "Author")
            LoremPictureLabel(label = "Width")
            LoremPictureLabel(label = "Height")
            LoremPictureLabel(label = "Download_url")
        }
        Spacer(modifier = Modifier.width(32.dp))
        Column() {
            LoremPictureData(value = loremPicture.id)
            LoremPictureData(value = loremPicture.author)
            LoremPictureData(value = loremPicture.width)
            LoremPictureData(value = loremPicture.height)
            LoremPictureData(value = loremPicture.downloadUrl)
        }
    }
}

@Composable
private fun LoremPictureLabel(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(
        text = label,
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.End
    )
}

@Composable
private fun LoremPictureData(
    modifier: Modifier = Modifier,
    value: String,
) {
    Text(
        text = value,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Start
    )
}