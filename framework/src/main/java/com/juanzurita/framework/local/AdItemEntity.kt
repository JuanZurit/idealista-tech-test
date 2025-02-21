package com.juanzurita.framework.local

import androidx.room.Entity
import com.juanzurita.domain.ad_list.models.AdItem
import com.juanzurita.domain.ad_list.models.Multimedia
import com.juanzurita.domain.ad_list.models.PriceInfo

@Entity(tableName = "ad_item", primaryKeys = ["propertyCode"])
data class AdItemEntity(
    val propertyCode: String,
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
    val features:Map<String,Boolean>? = null,
    val isFavorite:Int
){
    fun toDomain() = AdItem(
        propertyCode = propertyCode,
        thumbnail = thumbnail,
        floor = floor,
        price = price,
        priceInfo = priceInfo,
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
        multimedia = multimedia,
        features = features,
        isFavorite= isFavorite==1
    )
}
