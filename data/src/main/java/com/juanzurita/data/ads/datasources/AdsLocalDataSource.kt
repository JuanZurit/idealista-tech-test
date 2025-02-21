package com.juanzurita.data.ads.datasources

import com.juanzurita.domain.ad_list.models.AdItem
import kotlinx.coroutines.flow.Flow

interface AdsLocalDataSource{
    suspend fun fetchAdsList(): Flow<List<AdItem>>?
    suspend fun fetchFavoriteAdsList(): Flow<List<AdItem>>?
    suspend fun insertAds(ads: List<AdItem>):Boolean
    suspend fun updateFavoriteStatus(propertyCode:String, isFavorite:Boolean):Boolean
}