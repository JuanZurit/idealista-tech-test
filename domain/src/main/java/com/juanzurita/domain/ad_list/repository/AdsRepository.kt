package com.juanzurita.domain.ad_list.repository

import com.juanzurita.domain.ad_list.models.AdItem
import kotlinx.coroutines.flow.Flow


interface AdsRepository {
    suspend fun fetchAdsList(isFavorite:Boolean): Flow<List<AdItem>>
    suspend fun updateFavoriteAdsList(propertyCode:String, isFavorite:Boolean):Boolean
}