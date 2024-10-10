package com.selvaganesh.randomcityapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.selvaganesh.randomcityapp.db.dao.CountryDao
import com.selvaganesh.randomcityapp.db.data.CountryData


@Database(entities = [CountryData::class], version = 1)
abstract class DatabaseBuilder : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseBuilder? = null

        fun getDatabase(context: Context): DatabaseBuilder {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, DatabaseBuilder::class.java, "country_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

