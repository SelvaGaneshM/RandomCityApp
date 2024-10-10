package com.selvaganesh.randomcityapp.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "cityName") val cityName: String?,
    @ColumnInfo(name = "lat") val lat: String?,
    @ColumnInfo(name = "lng") val lng: String?
)