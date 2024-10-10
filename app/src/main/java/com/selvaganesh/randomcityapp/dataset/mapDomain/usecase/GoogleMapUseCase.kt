package com.selvaganesh.randomcityapp.dataset.mapDomain.usecase

import com.selvaganesh.randomcityapp.common.BaseResult
import com.selvaganesh.randomcityapp.common.utils.WrappedResponse
import com.selvaganesh.randomcityapp.dataset.mapDomain.GoogleMapsRepository
import com.selvaganesh.randomcityapp.dataset.mapDomain.entity.GoogleMapsEntity
import com.selvaganesh.randomcityapp.dataset.maps.remote.dto.GoogleMapsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoogleMapUseCase @Inject constructor(private val addVehicleRepository: GoogleMapsRepository) {

    suspend fun getCityDetails(
        cityName: String,
        key: String
    ): Flow<BaseResult<GoogleMapsEntity, WrappedResponse<ArrayList<GoogleMapsResponse>>>> {
        return addVehicleRepository.getCityDetails(cityName, key)
    }
}