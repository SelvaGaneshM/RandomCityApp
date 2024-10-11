package com.selvaganesh.randomcityapp.detailed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selvaganesh.randomcityapp.common.BaseResult
import com.selvaganesh.randomcityapp.dataset.mapDomain.entity.GoogleMapsEntity
import com.selvaganesh.randomcityapp.dataset.mapDomain.usecase.GoogleMapUseCase
import com.selvaganesh.randomcityapp.db.DatabaseBuilder
import com.selvaganesh.randomcityapp.db.data.CountryData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedScreenViewModel @Inject constructor(
    @ApplicationContext context: Context, private val googleMapUseCase: GoogleMapUseCase
) : ViewModel() {

    private val _dataSet = MutableStateFlow<GoogleMapsEntity?>(null)
    val dataSet get() = _dataSet.asStateFlow()
    val countryDatabase = DatabaseBuilder.getDatabase(context = context)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()


    fun getCityDetails(countryName: String, key: String) {
        viewModelScope.launch {
            googleMapUseCase.getCityDetails(countryName, key).onEach {
                when (it) {

                    is BaseResult.Success -> {
                        _dataSet.value = it.data
                        _isLoading.value = false
                    }

                    is BaseResult.Error -> {
                        _isLoading.value = false
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun insertData(cityName: String?, googleMapsEntity: GoogleMapsEntity) {
        viewModelScope.launch {
            cityName?.let {
                countryDatabase.countryDao().insertAll(
                    CountryData(
                        cityName = cityName,
                        lat = googleMapsEntity.lat,
                        lng = googleMapsEntity.lng
                    )
                )
                //Log.d("DB", "insertData: ${countryDatabase.countryDao().getAll()}")
            }
        }
    }
}

