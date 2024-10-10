package com.selvaganesh.randomcityapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.selvaganesh.randomcityapp.db.data.CountryData

@Dao
interface CountryDao {
    @Query("SELECT * FROM CountryData")
    suspend fun getAll(): List<CountryData>

    @Insert
    suspend fun insertAll(Courses: CountryData)

    @Delete
    suspend fun delete(countryData: CountryData)
}