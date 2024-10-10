package com.selvaganesh.randomcityapp.dataset.maps.remote.api

import com.selvaganesh.randomcityapp.common.utils.WrappedResponse
import com.selvaganesh.randomcityapp.dataset.maps.remote.dto.GoogleMapsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapsAPI {
    @GET("maps/api/geocode/json")
    suspend fun getCityDetails(
        @Query("address") address: String,
        @Query("key") key: String
    ): Response<WrappedResponse<ArrayList<GoogleMapsResponse>>>
}