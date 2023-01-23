package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kjk.lorempicsumapp.R
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.ui.presentation.home.LoremPictureViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: LoremPictureViewModel = hiltViewModel()
) {

    var pictureId by rememberSaveable {
        mutableStateOf(viewModel.getLoremPictureId())
    }

    viewModel.fetchPicture(pictureId)
    val loremPicture = viewModel.loremPicture.collectAsState()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Card {
            GlideImage(
                model = loremPicture.value.downloadUrl,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TwoButtons(
            onLeftIconClicked = {
                viewModel.setPreviousPictureId()
                pictureId = viewModel.getLoremPictureId()
            },
            onRightIconClicked = {
                viewModel.setNextPictureId()
                pictureId = viewModel.getLoremPictureId()
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        PictureInfo(loremPicture.value)

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
        ) {

        }
    }
}

@Composable
fun TwoButtons(
    modifier: Modifier = Modifier,
    onLeftIconClicked: () -> Unit = {},
    onRightIconClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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

@Composable
private fun PictureInfo(
    loremPicture: LoremPicture
) {
    Column {
        LoremPictureData(attribute = "ID", data = loremPicture.id)
        LoremPictureData(attribute = "Author", data = loremPicture.author)
        LoremPictureData(attribute = "width", data = loremPicture.width)
        LoremPictureData(attribute = "height", data = loremPicture.height)
        LoremPictureData(attribute = "downlod_url", data = loremPicture.downloadUrl)
    }
}

@Composable
private fun LoremPictureData(
    attribute: String,
    data: String
) {
    Row {
        Text(
            modifier = Modifier.width(45.dp),
            text = attribute,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.width(32.dp))
        Text(
            text = data,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwoButtons() {
    TwoButtons()
}