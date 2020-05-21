package com.bikersmode.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bikersmode.database.interfaces.LocationDao


/**
 * Created by Kumar Himanshu(himanshubit@gmail.com) on 25-11-2019.
 * Copyright (c) 2019. All rights reserved.
 */
@Database(entities = [Location::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): LocationDao
}