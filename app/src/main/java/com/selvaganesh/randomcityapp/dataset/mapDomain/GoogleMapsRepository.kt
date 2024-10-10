package com.selvaganesh.randomcityapp.dataset.mapDomain

import com.selvaganesh.randomcityapp.common.BaseResult
import com.selvaganesh.randomcityapp.common.utils.WrappedResponse
import com.selvaganesh.randomcityapp.dataset.mapDomain.entity.GoogleMapsEntity
import com.selvaganesh.randomcityapp.dataset.maps.remote.dto.GoogleMapsResponse
import kotlinx.coroutines.flow.Flow

interface GoogleMapsRepository {
    suspend fun getCityDetails(
        address: String, key: String
    ): Flow<BaseResult<GoogleMapsEntity, WrappedResponse<ArrayList<GoogleMapsResponse>>>>
}