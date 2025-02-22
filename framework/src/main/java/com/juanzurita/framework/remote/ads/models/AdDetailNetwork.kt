package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.AdDetail
import com.juanzurita.framework.remote.NetworkDTO
import com.squareup.moshi.Json

data class AdDetailNetwork(
    @Json(name = "adid")
    val id: Int? = null,
    val price: Float? = null,
    val priceInfo: PriceNetwork? = null,
    val operation: String? = null,
    val propertyType: String? = null,
    val extendedPropertyType: String? = null,
    val homeType: String? = null,
    val state: String? = null,
    val multimedia: MultimediaNetwork? = null,
    val propertyComment: String? = null,
    val ubication: Map<String?, String?>? = null,
    val country: String? = null,
    val moreCharacteristics: AdMoreCharacteristicsNetwork? = null,
    val energyCertification: AdEnergyCertificationNetwork? = null
) : NetworkDTO<AdDetail>() {
    override fun convert(): AdDetail = AdDetail(
        id = id,
        price = price,
        priceInfo = priceInfo?.convert(),
        operation = operation,
        propertyType = propertyType,
        extendedPropertyType = extendedPropertyType,
        homeType = homeType,
        state = state,
        multimedia = multimedia?.convert(),
        propertyComment = propertyComment,
        ubication = ubication,
        country = country,
        moreCharacteristics = moreCharacteristics?.convert(),
        energyCertification = energyCertification?.convert()
    )
}
