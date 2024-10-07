package com.selvaganesh.randomcityapp.dataset

import java.text.SimpleDateFormat
import java.util.Date

data class CityDataSource(
    var cityName: String, var randomColor: String, var dateTimeStamp: String
)

var cityDataSource: ArrayList<CityDataSource> = arrayListOf()
val cities = listOf(
    "New York", "Los Angeles", "Scranton", "Philadelphia", "Nashville", "Saint Louis", "Miami"
)
val colors = listOf("Yellow", "White", "Green", "Blue", "Red", "Black")

fun addCity(pickValue: Int): CityDataSource {
    var dataSetCity = CityDataSource(cities[pickValue], colors.random(), getDate())
    cityDataSource.add(dataSetCity)
    return dataSetCity
}

fun getCityData(): ArrayList<CityDataSource> {
    return cityDataSource
}

private fun getDate(): String {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
    val currentDate = sdf.format(Date())
    return currentDate
}