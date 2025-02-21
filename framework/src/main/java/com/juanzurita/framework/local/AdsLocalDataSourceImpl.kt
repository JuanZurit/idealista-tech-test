package com.juanzurita.framework.local

import android.content.Context
import com.juanzurita.data.ads.datasources.AdsLocalDataSource
import com.juanzurita.domain.ad_list.models.AdItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdsLocalDataSourceImpl @Inject constructor(@ApplicationContext context: Context) :
    AdsLocalDataSource {

    private var room: AdsDao? = null

    init {
        val database = IdealistaTechTestDatabase.getInstance(context)
        room = database?.adDao()
    }

    override suspend fun fetchAdsList(): Flow<List<AdItem>>? =
        room?.selectAlertCompetition()?.map { it.map { adItem -> adItem.toDomain() } }

    override suspend fun fetchFavoriteAdsList(): Flow<List<AdItem>>? =
        room?.selectFavoriteAds()?.map { it.map { adItem -> adItem.toDomain() } }

    override suspend fun insertAds(ads: List<AdItem>): Boolean {
        room?.insertAdsWithFavorite(ads.filter { !it.propertyCode.isNullOrEmpty() }
            .map { it.toEntity() })
        return true
    }

    override suspend fun updateFavoriteStatus(propertyCode: String, isFavorite: Boolean): Boolean {
        return ((room?.updateFavoriteStatus(propertyCode, if (isFavorite) 1 else 0)) ?: 0) >= 1
    }

    private fun AdItem.toEntity() = AdItemEntity(
        propertyCode = propertyCode.orEmpty(),
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
        isFavorite = if (isFavorite) 1 else 0
    )
}