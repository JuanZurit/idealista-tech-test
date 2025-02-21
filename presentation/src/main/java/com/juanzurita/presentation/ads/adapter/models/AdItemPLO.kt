package com.juanzurita.presentation.ads.adapter.models

import com.juanzurita.core.adapter.DelegateAdapterItem
import com.juanzurita.domain.ad_list.models.PriceInfo

data class AdItemPLO(
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
    val principalImage: String? = null,
    val secondaryImage1: String? = null,
    val secondaryImage2: String? = null,
    val secondaryImage3: String? = null,
    val features:Map<String,Boolean>? = null,
    val isFavorite:Boolean
): DelegateAdapterItem() {

    override fun id(): Any = "ad_item_${propertyCode}"

    override fun content(): Any = AdItemPLOContent(
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
        principalImage = principalImage,
        secondaryImage1 = secondaryImage1,
        secondaryImage2 = secondaryImage2,
        secondaryImage3 = secondaryImage3,
        features = features,
        isFavorite = isFavorite
    )

    override fun copy(): DelegateAdapterItem = AdItemPLO(
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
        principalImage = principalImage,
        secondaryImage1 = secondaryImage1,
        secondaryImage2 = secondaryImage2,
        secondaryImage3 = secondaryImage3,
        features = features,
        isFavorite = isFavorite
    )

    // Clase interna para representar el contenido
    inner class AdItemPLOContent(
        val propertyCode: String?, val thumbnail: String?, val floor: String?,
        val price: Float?, val priceInfo: PriceInfo?, val propertyType: String?,
        val operation: String?, val size: Int?, val exterior: Boolean?,
        val rooms: Int?, val bathrooms: Int?, val address: String?,
        val province: String?, val municipality: String?, val district: String?,
        val country: String?, val neighborhood: String?, val latitude: Float?,
        val longitude: Float?, val description: String?, val principalImage: String?,
        val secondaryImage1: String?, val secondaryImage2: String?,
        val secondaryImage3: String?, val features: Map<String, Boolean>?,
        val isFavorite: Boolean
    ) {

        override fun equals(other: Any?): Boolean {
            return if (other is AdItemPLOContent) {
                propertyCode == other.propertyCode &&
                        thumbnail == other.thumbnail &&
                        floor == other.floor &&
                        price == other.price &&
                        priceInfo == other.priceInfo &&
                        propertyType == other.propertyType &&
                        operation == other.operation &&
                        size == other.size &&
                        exterior == other.exterior &&
                        rooms == other.rooms &&
                        bathrooms == other.bathrooms &&
                        address == other.address &&
                        province == other.province &&
                        municipality == other.municipality &&
                        district == other.district &&
                        country == other.country &&
                        neighborhood == other.neighborhood &&
                        latitude == other.latitude &&
                        longitude == other.longitude &&
                        description == other.description &&
                        principalImage == other.principalImage &&
                        secondaryImage1 == other.secondaryImage1 &&
                        secondaryImage2 == other.secondaryImage2 &&
                        secondaryImage3 == other.secondaryImage3 &&
                        features == other.features &&
                        isFavorite == other.isFavorite
            } else {
                false
            }
        }

        override fun hashCode(): Int {
            return propertyCode.hashCode() +
                    thumbnail.hashCode() +
                    floor.hashCode() +
                    price.hashCode() +
                    priceInfo.hashCode() +
                    propertyType.hashCode() +
                    operation.hashCode() +
                    size.hashCode() +
                    exterior.hashCode() +
                    rooms.hashCode() +
                    bathrooms.hashCode() +
                    address.hashCode() +
                    province.hashCode() +
                    municipality.hashCode() +
                    district.hashCode() +
                    country.hashCode() +
                    neighborhood.hashCode() +
                    latitude.hashCode() +
                    longitude.hashCode() +
                    description.hashCode() +
                    principalImage.hashCode() +
                    secondaryImage1.hashCode() +
                    secondaryImage2.hashCode() +
                    secondaryImage3.hashCode() +
                    features.hashCode() +
                    isFavorite.hashCode()
        }
    }



}
