package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.juanzurita.domain.ad_list.models.AdMoreCharacteristics
import com.juanzurita.presentation.R
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens

@Composable
fun MoreCharacteristicsView(
    characteristics: AdMoreCharacteristics,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(Dimens.Spacing.extraSmall)) {
        Text(
            text = stringResource(R.string.more_characteristics_title),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(Dimens.Spacing.small))

        CharacteristicItem(
            label = stringResource(R.string.built_area_label),
            value = "${characteristics.constructedArea} m²"
        )
        CharacteristicItem(
            label = stringResource(R.string.energy_certification_label),
            value = characteristics.energyCertificationType.orEmpty()
        )
        CharacteristicItem(
            label = stringResource(R.string.community_costs_label),
            value = "€${characteristics.communityCosts}"
        )
    }
}

@Composable
fun CharacteristicItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.padding(vertical = Dimens.Spacing.extraSmall)
    )
}