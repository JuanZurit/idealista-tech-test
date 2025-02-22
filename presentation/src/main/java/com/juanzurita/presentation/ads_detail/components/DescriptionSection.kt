package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens
import com.juanzurita.domain.ad_list.models.AdDetail

@Composable
fun DescriptionSection(adDetail: AdDetail?,modifier: Modifier=Modifier) {
    adDetail?.propertyComment?.takeIf { it.isNotEmpty() }?.let { description ->
        Spacer(modifier = modifier.height(Dimens.Spacing.large))
        Text(text = description, style = MaterialTheme.typography.bodyMedium, modifier = modifier.padding(horizontal = Dimens.Spacing.extraSmall))
    }
}
