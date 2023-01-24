package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.ui.presentation.home.LoremPictureViewModel
import timber.log.Timber

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: LoremPictureViewModel = hiltViewModel()
) {

    val pictureId = viewModel.getLoremPictureId()
    Timber.d("pictureId ::  ${pictureId}")
    viewModel.fetchPicture(pictureId)

    val loremPicture = viewModel.loremPicture.collectAsState()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            GlideImage(
                model = loremPicture.value.downloadUrl,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        PictureInfo(loremPicture.value)
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
    data : String
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