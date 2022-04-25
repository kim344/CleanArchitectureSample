package com.kim344.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kim344.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}