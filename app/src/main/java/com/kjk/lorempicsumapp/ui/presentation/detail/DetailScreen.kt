package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kjk.lorempicsumapp.domain.entity.LoremPictureUiModel
import com.kjk.lorempicsumapp.ui.presentation.common.LoremPictureImageWrapper

// TODO 실제 data로 변경해야함
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    loremPicture: LoremPictureUiModel
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        LoremPictureImageWrapper(loremPicture = loremPicture)
        Spacer(modifier = Modifier.height(32.dp))
        PictureInfo(loremPicture)
    }
}

@Composable
private fun PictureInfo(
    loremPicture: LoremPictureUiModel
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        loremPicture = LoremPictureUiModel()
    )
}