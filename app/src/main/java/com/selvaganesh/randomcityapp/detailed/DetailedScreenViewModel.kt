package com.selvaganesh.randomcityapp.detailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selvaganesh.randomcityapp.common.BaseResult
import com.selvaganesh.randomcityapp.dataset.mapDomain.entity.GoogleMapsEntity
import com.selvaganesh.randomcityapp.dataset.mapDomain.usecase.GoogleMapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedScreenViewModel @Inject constructor(private val googleMapUseCase: GoogleMapUseCase) :
    ViewModel() {

    private val _dataSet = MutableStateFlow<GoogleMapsEntity?>(null)
    val dataSet get() = _dataSet.asStateFlow()

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
}

