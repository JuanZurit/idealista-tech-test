package com.juanzurita.presentation.ads_detail

import android.content.res.Configuration
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juanzurita.presentation.R
import com.juanzurita.presentation.ads_detail.components.AdDetailsContent
import com.juanzurita.presentation.ads_detail.components.ImageCarousel
import com.juanzurita.presentation.ads_detail.components.LoadingView
import com.juanzurita.presentation.ads_detail.components.NoDataView
import com.juanzurita.presentation.ads_detail.components.Toolbar

@Composable
fun AdDetailScreen(innerPadding: PaddingValues, viewModel: AdsDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val imageUrls = uiState.carouselImages
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { imageUrls.size })
    val isCollapsed = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val cutoutPadding = WindowInsets.displayCutout.asPaddingValues()
    val systemBarPadding = WindowInsets.systemBars.asPaddingValues()


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
            if (!isCollapsed) {
                ImageCarousel(
                    pagerState = pagerState,
                    imageUrls = imageUrls,
                    onBackPressed = { backDispatcher?.onBackPressed() },
                    height = 270.dp
                )
            } else {
                Toolbar(
                    title = stringResource(R.string.ad_details_title),
                    onBackPressed = { backDispatcher?.onBackPressed() })
            }

            if (uiState.isLoading) LoadingView()
            else if (uiState.noData) NoDataView()
            else AdDetailsContent(
                uiState.adDetail,
                modifier = Modifier.padding(bottom = cutoutPadding.calculateBottomPadding() + systemBarPadding.calculateBottomPadding())
            )
        }
    }
}
