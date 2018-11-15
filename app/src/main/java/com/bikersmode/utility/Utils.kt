package com.bikersmode.utility

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.net.Uri.withAppendedPath
import android.os.Build
import android.support.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


/**
 * Created by kumarhimanshu on 23/5/18.
 */
class Utils {
    companion object {
        var isBackAllow:Boolean?= null
        fun getContactName(phoneNumber: String, context: Context): String {
            val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))

            val projection = arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME)

            var contactName = ""
            val cursor = context.getContentResolver().query(uri, projection, null, null, null)

            if (cursor != null) {
                if (cursor!!.moveToFirst()) {
                    contactName = cursor!!.getString(0)
                }
                cursor!!.close()
            }

            return contactName

        }

        fun getContactTime(): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
            val cal = Calendar.getInstance()
            val tk = dateFormat.format(cal.time)
            println("Time Display: " + tk) // <-- I got result here
            //val date = tk.nextToken()
            //val time = tk.nextToken()

//            val sdf = SimpleDateFormat("hh:mm:ss",Locale.US)
//            val sdfs = SimpleDateFormat("hh:mm a",Locale.US)
//            val dt: Date
//            var timeString: String = ""
//            try {
//                dt = sdf.parse(tk)
//                timeString = sdfs.format(dt)
//                println("Time Display: " + sdfs.format(dt)) // <-- I got result here
//            } catch (e: Exception) {
//                // TODO Auto-generated catch block
//                e.printStackTrace()
//            }
            return tk
        }

    }
}