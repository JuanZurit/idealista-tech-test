package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.Price
import com.juanzurita.framework.remote.NetworkDTO

data class PriceNetwork(
    val amount: Float? = null,
    val currencySuffix: String? = null,
) : NetworkDTO<Price>() {
    override fun convert() = Price(
        amount = amount,
        currency = currencySuffix
    )
}
