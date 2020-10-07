package com.dicoding.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.core.data.local.entity.KamusEntity

@Database(entities = [KamusEntity::class], version = 1, exportSchema = false)
abstract class KamusDatabase : RoomDatabase() {

    abstract fun kamusDao(): KamusDao

}