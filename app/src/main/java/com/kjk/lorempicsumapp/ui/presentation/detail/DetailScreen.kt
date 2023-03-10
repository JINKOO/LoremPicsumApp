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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kjk.lorempicsumapp.R
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import timber.log.Timber

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel
) {

    val detailUiState by viewModel.detailUiState.collectAsState()
    val currLoremPicture = detailUiState.currLoremPicture
    val prevLoremPicture = detailUiState.prevLoremPicture
    val nextLoremPicture = detailUiState.nextLoremPicture

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
            ImageThumbNails(pictureUrl = currLoremPicture?.downloadUrl)
        }

        if (currLoremPicture != null) {
            PictureInfo(loremPicture = currLoremPicture)
        }
        TwoButtons(
            // TODO ?????? :: ?????? ?????? Composable??? ?????? ??? ???, uiState??? ???????????? ??????? JetNews?????? ??????????????? ?????????.
//            detailUiState = detailUiState,
            onLeftIconClicked = {
                prevLoremPicture?.let {
                    viewModel.fetchLoremPicture(it.id)
                }
            },
            onRightIconClicked = {
                nextLoremPicture?.let {
                    viewModel.fetchLoremPicture(it.id)
                }
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ImageThumbNails(
                pictureUrl = prevLoremPicture?.downloadUrl,
                width = 70,
                height = 50
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageThumbNails(
                pictureUrl = currLoremPicture?.downloadUrl,
                width = 70,
                height = 50
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageThumbNails(
                pictureUrl = nextLoremPicture?.downloadUrl,
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
        modifier = Modifier
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
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {
                    onLeftIconClicked()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_left_24),
                    contentDescription = null
                )
            }
        }
        // ?????? ?????? ??? ??? ?????? ????????
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

// TODO 0??? detail ?????? update
@Composable
private fun PictureInfo(
    loremPicture: LoremPicture
) {
    Row() {
        // ?????? title label ??????
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