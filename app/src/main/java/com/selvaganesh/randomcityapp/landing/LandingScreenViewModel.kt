package com.selvaganesh.randomcityapp.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor() : ViewModel() {

    private val _countDownFlow = MutableStateFlow<String>("")
    val countDownFlow: StateFlow<String> = _countDownFlow.asStateFlow()


    init {
        startCountDown(5)
    }

    fun startCountDown(totalTimeInSeconds: Int) {
        viewModelScope.launch {
            getCountDownTimerFlow(totalTimeInSeconds).collect { time ->
                _countDownFlow.value = time
            }
        }
    }

    private fun getCountDownTimerFlow(totalTimeInSeconds: Int): Flow<String> =
        flow {
            for (i in totalTimeInSeconds downTo 0) {
                delay(1000L)
                val minutes = i / 60
                val seconds = i % 60
                emit(String.format("%02d", seconds))
            }
        }


}