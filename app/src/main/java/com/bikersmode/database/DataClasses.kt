package com.bikersmode.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Kumar Himanshu(himanshubit@gmail.com) on 25-11-2019.
 * Copyright (c) 2019. All rights reserved.
 */
@Entity
data class Location(@PrimaryKey val uid: Int,
                    @ColumnInfo(name = "start_lat") val startLat: Float,
                    @ColumnInfo(name = "start_long") val startLong: Float,
                    @ColumnInfo(name = "end_lat") val endLat: Float,
                    @ColumnInfo(name = "end_long") val endLong: Float,
                    @ColumnInfo(name = "start_time") val startTime: String,
                    @ColumnInfo(name = "end_time") val endTime: String)