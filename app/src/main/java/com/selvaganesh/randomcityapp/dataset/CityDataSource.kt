package com.selvaganesh.randomcityapp.dataset

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Date

data class CityDataSource(
    var cityName: String, var color: Color, var dateTimeStamp: String
)

var cityDataSource: ArrayList<CityDataSource> = arrayListOf()
val cities = listOf(
    "New York", "Los Angeles", "Scranton", "Philadelphia", "Nashville", "Saint Louis", "Miami"
)
val colors = listOf(Color.Yellow, Color.White, Color.Green, Color.Blue, Color.Red, Color.Black)

fun addCity(pickValue: Int): CityDataSource {
    var dataSetCity = CityDataSource(cities[pickValue], colors.random(), getDate())
    cityDataSource.add(dataSetCity)
    return dataSetCity
}

fun getCityData(): ArrayList<CityDataSource> {
    return cityDataSource
}

private fun getDate(): String {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())
    return currentDate
}