package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.AdItem
import com.juanzurita.framework.remote.NetworkDTO

data class AdItemNetwork(
    val propertyCode: String? = null,
    val thumbnail: String? = null,
    val floor: String? = null,
    val price: Float? = null,
    val priceInfo: PriceInfoNetwork? = null,
    val propertyType: String? = null,
    val operation: String? = null,
    val size: Int? = null,
    val exterior: Boolean? = null,
    val rooms: Int? = null,
    val bathrooms: Int? = null,
    val address: String? = null,
    val province: String? = null,
    val municipality: String? = null,
    val district: String? = null,
    val country: String? = null,
    val neighborhood: String? = null,
    val latitude: Float? = null,
    val longitude: Float? = null,
    val description: String? = null,
    val multimedia: MultimediaNetwork? = null,
    val features: Map<String, Boolean>? = null,
) : NetworkDTO<AdItem>() {
    override fun convert() = AdItem(
        propertyCode = propertyCode,
        thumbnail = thumbnail,
        floor = floor,
        price = price,
        priceInfo = priceInfo?.convert(),
        propertyType = propertyType,
        operation = operation,
        size = size,
        exterior = exterior,
        rooms = rooms,
        bathrooms = bathrooms,
        address = address,
        province = province,
        municipality = municipality,
        district = district,
        country = country,
        neighborhood = neighborhood,
        latitude = latitude,
        longitude = longitude,
        description = description,
        multimedia = multimedia?.convert(),
        features = features,
    )

}
