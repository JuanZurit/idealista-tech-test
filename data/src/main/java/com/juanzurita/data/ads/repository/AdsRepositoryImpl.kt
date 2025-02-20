package com.juanzurita.data.ads.repository

import arrow.core.Either
import com.juanzurita.core.domain.models.Error
import com.juanzurita.data.ads.datasources.AdsRemoteDataSource
import com.juanzurita.domain.ad_list.models.AdItem
import com.juanzurita.domain.ad_list.repository.AdsRepository
import javax.inject.Inject

class AdsRepositoryImpl @Inject constructor(
    private val adsRemoteDataSource: AdsRemoteDataSource
): AdsRepository {

    override suspend fun fetchAdsList(): Either<Error, List<AdItem>> = adsRemoteDataSource.fetchAdsList()

}