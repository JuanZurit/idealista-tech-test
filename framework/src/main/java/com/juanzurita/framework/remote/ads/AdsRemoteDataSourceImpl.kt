package com.juanzurita.framework.remote.ads

import arrow.core.Either
import com.juanzurita.core.domain.models.Error
import com.juanzurita.core.framework.managers.DispatcherProvider
import com.juanzurita.core.util.extensions.tryCall
import com.juanzurita.data.ads.datasources.AdsRemoteDataSource
import com.juanzurita.domain.ad_list.models.AdDetail
import com.juanzurita.domain.ad_list.models.AdItem
import com.juanzurita.framework.remote.ApiEndpoints
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdsRemoteDataSourceImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val apiEndpoints: ApiEndpoints,
): AdsRemoteDataSource {

    override suspend fun fetchAdsList(): Either<Error, List<AdItem>> = withContext(dispatcherProvider.io){
        tryCall{
            apiEndpoints.fetchAdList().map { it.convert() }
        }
    }

    override suspend fun fetchAdDetail(): Either<Error, AdDetail> = withContext(dispatcherProvider.io){
        tryCall{
            apiEndpoints.fetchAdDetail().convert()
        }
    }


}