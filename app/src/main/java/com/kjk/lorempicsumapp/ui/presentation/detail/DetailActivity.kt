package com.kjk.lorempicsumapp.ui.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO

class DetailActivity : ComponentActivity() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailScreen(
                loremPicture = viewModel.detailData ?: LoremPictureVO()
            )
        }
    }

    companion object {
        fun getDetailPicture(
            context: Context,
            loremPictureVO: LoremPictureVO
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailViewModel.PICTURE_DETAIL_DATA, loremPictureVO)
            }
        }
    }
}