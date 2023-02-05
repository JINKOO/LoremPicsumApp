package com.kjk.lorempicsumapp.ui.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonProgressBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}