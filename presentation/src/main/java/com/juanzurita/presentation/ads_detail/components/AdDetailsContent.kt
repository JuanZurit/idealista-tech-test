package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juanzurita.domain.ad_list.models.AdDetail
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens

@Composable
fun AdDetailsContent(
    adDetail: AdDetail?,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val mapState = rememberMapState(adDetail?.ubication)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = Dimens.Spacing.medium)
    ) {
        adDetail?.let { RoomAndBathroomAndPriceSection(it) }
        DescriptionSection(adDetail)
        CharacteristicsSection(adDetail)
        EnergyCertificationSection(adDetail)
        MapSection(adDetail, mapState)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

/*Option with LazyColumn*/
/*
package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juanzurita.domain.ad_list.models.AdDetail
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens

@Composable
fun AdDetailsContent(
    adDetail: AdDetail?,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    val mapState = rememberMapState(adDetail?.ubication)

    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.Spacing.medium)
    ) {
        item(key = "header_section"){ adDetail?.let { RoomAndBathroomAndPriceSection(it) } }
        item(key = "description_section") { DescriptionSection(adDetail) }
        item(key = "characteristics_section") { CharacteristicsSection(adDetail) }
        item(key = "energy_section"){ EnergyCertificationSection(adDetail) }
        item(key = "map_section") { MapSection(adDetail, mapState) }
        item { Spacer(modifier=Modifier.height(24.dp)) }
    }
}
*/