package com.juanzurita.domain.ad_list.models

data class AdItem(
    val propertyCode: String? = null,
    val thumbnail: String? = null,
    val floor: String? = null,
    val price: Float? = null,
    val priceInfo: PriceInfo? = null,
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
    val multimedia: Multimedia?= null,
    val features:Map<String,Boolean>? = null
)
