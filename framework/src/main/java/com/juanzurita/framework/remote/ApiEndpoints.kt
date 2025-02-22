package com.juanzurita.framework.remote

import com.juanzurita.framework.remote.ads.models.AdDetailNetwork
import com.juanzurita.framework.remote.ads.models.AdItemNetwork
import retrofit2.http.GET


interface ApiEndpoints {
    companion object {

        private const val API_DOMAIN_CONTROLLER: String = "/android-challenge/"
    }

    @GET(API_DOMAIN_CONTROLLER+ApiConstants.Paths.AD_LIST)
    suspend fun fetchAdList():List<AdItemNetwork>

    @GET(API_DOMAIN_CONTROLLER+ApiConstants.Paths.AD_DETAIL)
    suspend fun fetchAdDetail(): AdDetailNetwork


}