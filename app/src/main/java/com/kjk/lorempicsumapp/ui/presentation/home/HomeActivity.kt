package com.kjk.lorempicsumapp.ui.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.kjk.lorempicsumapp.domain.entity.LoremPictureUiModel
import com.kjk.lorempicsumapp.ui.presentation.detail.DetailActivity
import com.kjk.lorempicsumapp.ui.theme.LoremPicsumAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoremPicsumAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(
                        navigateToDetail = {
                            navigateToDetail(it)
                        }
                    )
                }
            }
        }
    }

    private fun navigateToDetail(loremPictureVO: LoremPictureUiModel) {
        val intent = DetailActivity.getDetailPicture(
            context = this,
            loremPictureVO = loremPictureVO
        )
        startActivity(intent)
    }
}