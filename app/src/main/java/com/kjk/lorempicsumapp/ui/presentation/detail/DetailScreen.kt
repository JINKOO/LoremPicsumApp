package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kjk.lorempicsumapp.R

// TODO 실제 data로 변경해야함
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        Image(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(),
            painter = painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            contentDescription = null
        )
        PictureInfo()
    }
}

@Composable
private fun PictureInfo() {
    Column {
        // TODO 하드코딩 된 값 변경
        Text(
            text = "ID : "
        )

        Text(
            text = "Author : "
        )

        Text(
            text = "width : "
        )

        Text(
            text = "height : "
        )

        Text(
            text = "url : "
        )

        Text(
            text = "downlaod_url : "
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}