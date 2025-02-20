package com.juanzurita.data.ads.datasources

import arrow.core.Either
import com.juanzurita.core.domain.models.Error
import com.juanzurita.domain.ad_list.models.AdItem

interface AdsRemoteDataSource{
    suspend fun fetchAdsList(): Either<Error, List<AdItem>>
}