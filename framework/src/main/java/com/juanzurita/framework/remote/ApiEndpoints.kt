package com.juanzurita.framework.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface ApiEndpoints {
    companion object {
        private const val API_PATH = "/scripts/api/"
        private const val API_FILE = "api.php?"
        private const val API_DOMAIN_CONTROLLER: String = API_PATH + API_FILE
    }

    /*@GET(API_DOMAIN_CONTROLLER)
    suspend fun fetchMatchesDays(*/


}