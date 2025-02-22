package com.juanzurita.presentation.ads_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juanzurita.core.util.extensions.formatWithSeparators
import com.juanzurita.presentation.R
import com.juanzurita.domain.ad_list.models.AdDetail
import com.juanzurita.presentation.ads_detail.ui.theme.Dimens

@Composable
fun RoomAndBathroomAndPriceSection(
    adDetail: AdDetail,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = Dimens.Spacing.extraSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            adDetail.moreCharacteristics?.roomNumber?.let {
                RoomOrBathroomInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.iv_bedroom),
                    number = it,
                    modifier = modifier
                )
            }
            if (adDetail.moreCharacteristics?.roomNumber != null && adDetail.moreCharacteristics?.bathNumber != null) {
                Spacer(modifier = Modifier.width(16.dp))
            }
            adDetail.moreCharacteristics?.bathNumber?.let {
                RoomOrBathroomInfo(
                    icon = ImageVector.vectorResource(id = R.drawable.iv_bathroom),
                    number = it,
                    modifier = modifier
                )
            }
        }

        PriceSection(price = adDetail.price, modifier = modifier)
    }
}

@Composable
fun RoomOrBathroomInfo(icon: ImageVector, number: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = number.toString(), fontSize = 16.sp)
    }
}


@Composable
fun PriceSection(price: Float?, modifier: Modifier = Modifier) {
    Text(
        text = "$${price?.formatWithSeparators()}",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier.height(18.dp),
    )
}



