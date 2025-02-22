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
import com.juanzurita.domain.ad_list.models.AdEnergyCertification
import com.juanzurita.presentation.R
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens

@Composable
fun EnergyCertificationView(
    energyCertification: AdEnergyCertification,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(Dimens.Spacing.small)) {
        Text(
            text = stringResource(R.string.energy_certification_title),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(Dimens.Spacing.small))
        
        energyCertification.energyConsumption?.type?.let { type ->
            CharacteristicItem(
                label = stringResource(R.string.energy_type_label),
                value = type
            )
        }
        
        energyCertification.emissions?.type?.let { emissions ->
            CharacteristicItem(
                label = stringResource(R.string.emissions_label),
                value = emissions
            )
        }
    }
}