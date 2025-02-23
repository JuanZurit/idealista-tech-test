package com.juanzurita.presentation.ads_detail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juanzurita.presentation.R
import com.juanzurita.presentation.ads_detail.components.AdDetailsContent
import com.juanzurita.presentation.ads_detail.components.ImageCarousel
import com.juanzurita.presentation.ads_detail.components.LoadingView
import com.juanzurita.presentation.ads_detail.components.NoDataView
import com.juanzurita.presentation.ads_detail.components.Toolbar
import kotlin.math.min

@Composable
fun AdDetailScreen(innerPadding: PaddingValues, viewModel: AdsDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val imageUrls = uiState.carouselImages
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { imageUrls.size })
    val cutoutPadding = WindowInsets.displayCutout.asPaddingValues()
    val systemBarPadding = WindowInsets.systemBars.asPaddingValues()
    val lazyListState = rememberLazyListState()
    val scrollOffset by remember {
        derivedStateOf {
            val indexOffset = lazyListState.firstVisibleItemIndex.toFloat()
            val itemScrollOffset = lazyListState.firstVisibleItemScrollOffset / 200f
            (1 - (itemScrollOffset + indexOffset)).coerceIn(0f, 1f)
        }
    }

    val imageSize by animateDpAsState(targetValue = max(0.dp, 300.dp * scrollOffset))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(
                start = cutoutPadding.calculateStartPadding(LocalLayoutDirection.current) + systemBarPadding.calculateStartPadding(
                    LocalLayoutDirection.current
                ),
                end = cutoutPadding.calculateEndPadding(LocalLayoutDirection.current) + systemBarPadding.calculateEndPadding(
                    LocalLayoutDirection.current
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(contentAlignment = Center) {
                Toolbar(
                    title = stringResource(R.string.ad_details_title),
                    onBackPressed = { backDispatcher?.onBackPressed() },
                    imageSize = imageSize
                )

                ImageCarousel(
                    pagerState = pagerState,
                    imageUrls = imageUrls,
                    onBackPressed = { backDispatcher?.onBackPressed() },
                    height = imageSize
                )

            }
            if (uiState.isLoading) LoadingView()
            else if (uiState.noData) NoDataView()
            else AdDetailsContent(
                adDetail = uiState.adDetail,
                lazyListState = lazyListState,
                modifier = Modifier.padding(bottom = cutoutPadding.calculateBottomPadding() + systemBarPadding.calculateBottomPadding())
            )
        }
    }
}
