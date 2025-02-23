package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp

@Composable
fun ImageCarousel(
    pagerState:PagerState,
    imageUrls: List<String>,
    onBackPressed: () -> Unit,
    height: Dp) {

    if(height>=100.dp) {

        Box(modifier = Modifier.fillMaxWidth().height(height)) {
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrls[page]),
                    contentDescription = "Imagen de la propiedad",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            BackButton(onBackPressed)
        }
    }
}

@Composable
fun BackButton(onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .statusBarsPadding()
    ) {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier
                .align(Alignment.TopStart)
                .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
                .size(35.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
