package com.selvaganesh.randomcityapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selvaganesh.randomcityapp.db.dao.CountryDao
import com.selvaganesh.randomcityapp.db.data.CountryData

@Database(entities = [CountryData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CountryDao(): CountryDao
}