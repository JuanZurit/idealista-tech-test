package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens
import com.juanzurita.domain.ad_list.models.AdDetail

@Composable
fun CharacteristicsSection(adDetail: AdDetail?) {
    adDetail?.moreCharacteristics?.let {
        Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
        MoreCharacteristicsView(characteristics = it, modifier = Modifier.fillMaxWidth())
    }
}
