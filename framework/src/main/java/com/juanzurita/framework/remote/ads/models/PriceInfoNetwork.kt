package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.PriceInfo
import com.juanzurita.framework.remote.NetworkDTO

data class PriceInfoNetwork(
    val price: PriceNetwork? = null,
) : NetworkDTO<PriceInfo>() {

    override fun convert() = PriceInfo(
        price = price?.convert()
    )

}
