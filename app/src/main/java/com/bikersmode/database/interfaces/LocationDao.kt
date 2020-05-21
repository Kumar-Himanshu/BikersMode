package com.bikersmode.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bikersmode.database.Location


/**
 * Created by Kumar Himanshu(himanshubit@gmail.com) on 25-11-2019.
 * Copyright (c) 2019. All rights reserved.
 */
@Dao
interface LocationDao {
    @Query("SELECT * FROM location")
    fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Location>

    @Query("SELECT * FROM location WHERE uid LIKE :id LIMIT 1")
    fun findById(id: Int): Location

    @Insert
    fun insertAll(vararg locations: Location)

    @Delete
    fun delete(location: Location)
}