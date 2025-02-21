package com.juanzurita.data.ads.repository

import android.util.Log
import com.juanzurita.data.ads.datasources.AdsLocalDataSource
import com.juanzurita.data.ads.datasources.AdsRemoteDataSource
import com.juanzurita.domain.ad_list.models.AdItem
import com.juanzurita.domain.ad_list.repository.AdsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdsRepositoryImpl @Inject constructor(
    private val adsRemoteDataSource: AdsRemoteDataSource,
    private val adsLocalDataSource: AdsLocalDataSource,
) : AdsRepository {

    override suspend fun fetchAdsList(isFavorite: Boolean): Flow<List<AdItem>> {
        return ((if (isFavorite) adsLocalDataSource.fetchFavoriteAdsList() else adsLocalDataSource.fetchAdsList())?.map {
            it
        } ?: flow {
            emit(fetchAdListFromRemote())
        }).apply {
            adsLocalDataSource.insertAds(fetchAdListFromRemote())
        }
    }

    override suspend fun updateFavoriteAdsList(propertyCode: String, isFavorite: Boolean): Boolean {
        return adsLocalDataSource.updateFavoriteStatus(propertyCode, isFavorite)
    }

    private suspend fun fetchAdListFromRemote(): List<AdItem> {
        val result = adsRemoteDataSource.fetchAdsList().fold(
            ifLeft = {
                Log.d("ERROR", it.toString())
                emptyList()
            },
            ifRight = { it }
        )
        return result
    }
}