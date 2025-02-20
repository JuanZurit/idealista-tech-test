package com.juanzurita.domain.ad_list.repository

import arrow.core.Either
import com.juanzurita.core.domain.models.Error
import com.juanzurita.domain.ad_list.models.AdItem



interface AdsRepository {
    suspend fun fetchAdsList(): Either<Error, List<AdItem>>
}