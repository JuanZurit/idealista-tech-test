package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens
import com.juanzurita.domain.ad_list.models.AdDetail

@Composable
fun DescriptionSection(adDetail: AdDetail?) {
    adDetail?.propertyComment?.takeIf { it.isNotEmpty() }?.let { description ->
        Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
        Text(text = description, style = MaterialTheme.typography.bodyMedium)
    }
}
