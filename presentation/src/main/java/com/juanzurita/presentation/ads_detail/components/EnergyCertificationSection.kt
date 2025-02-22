package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens
import com.juanzurita.domain.ad_list.models.AdDetail

@Composable
fun EnergyCertificationSection(adDetail: AdDetail?,modifier: Modifier=Modifier) {
    adDetail?.energyCertification?.let {
        Spacer(modifier = modifier.height(Dimens.Spacing.medium))
        EnergyCertificationView(energyCertification = it, modifier = modifier.fillMaxWidth())
    }
}
