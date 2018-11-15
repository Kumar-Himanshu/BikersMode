package com.bikersmode.database

import android.provider.BaseColumns

object DBContract {
    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "callLogs"
            val ID = "id"
            val COLUMN_MOBILE_NUMBER = "mobileNumber"
            val COLUMN_NAME = "name"
            val COLUMN_TIME = "time"
        }
    }
}