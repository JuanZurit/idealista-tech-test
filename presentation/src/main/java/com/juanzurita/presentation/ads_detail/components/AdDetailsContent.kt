package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juanzurita.domain.ad_list.models.AdDetail
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens

@Composable
fun AdDetailsContent(
    adDetail: AdDetail?,
    modifier: Modifier = Modifier
) {
    val mapState = rememberMapState(adDetail?.ubication)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.Spacing.medium)
    ) {
        item { adDetail?.let { RoomAndBathroomAndPriceSection(it) } }
        item { DescriptionSection(adDetail) }
        item { CharacteristicsSection(adDetail) }
        item { EnergyCertificationSection(adDetail) }
        item { MapSection(adDetail, mapState) }
        item { Spacer(modifier=Modifier.height(24.dp)) }
    }
}
