package com.selvaganesh.randomcityapp.dataset.maps.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.selvaganesh.randomcityapp.common.BaseResult
import com.selvaganesh.randomcityapp.common.utils.WrappedResponse
import com.selvaganesh.randomcityapp.dataset.mapDomain.GoogleMapsRepository
import com.selvaganesh.randomcityapp.dataset.mapDomain.entity.GoogleMapsEntity
import com.selvaganesh.randomcityapp.dataset.maps.remote.api.GoogleMapsAPI
import com.selvaganesh.randomcityapp.dataset.maps.remote.dto.GoogleMapsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GoogleMapsRepositoryImpl @Inject constructor(private val addDriverAPI: GoogleMapsAPI) :
    GoogleMapsRepository {

    override suspend fun getCityDetails(
        address: String, key: String
    ): Flow<BaseResult<GoogleMapsEntity, WrappedResponse<ArrayList<GoogleMapsResponse>>>> {
        return flow {
            val response = addDriverAPI.getCityDetails(address, key)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.data != null && body?.data?.size!! > 0) {
                    val addDriver = GoogleMapsEntity(
                        body?.data?.get(0)?.geometry?.location?.lat.toString(),
                        body?.data?.get(0)?.geometry?.location?.lng.toString()
                    )
                    emit(BaseResult.Success(addDriver))
                }
            } else {
                val type =
                    object : TypeToken<WrappedResponse<ArrayList<GoogleMapsResponse>>>() {}.type
                val err: WrappedResponse<ArrayList<GoogleMapsResponse>> =
                    Gson().fromJson(response.errorBody()!!.charStream(), type)
                err.codes = response.code().toString()
                emit(BaseResult.Error(err))
            }
        }
    }
}