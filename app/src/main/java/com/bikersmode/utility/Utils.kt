package com.bikersmode.utility

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bikersmode.BaseActivity
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by kumarhimanshu on 23/5/18.
 */
class Utils {
    companion object {
        val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
        var isBackAllow:Boolean?= null
        fun getContactName(phoneNumber: String, context: Context): String {
            val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))

            val projection = arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME)

            var contactName = ""
            val cursor = context.contentResolver.query(uri, projection, null, null, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    contactName = cursor.getString(0)
                }
                cursor.close()
            }

            return contactName

        }

        fun getContactTime(): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
            val cal = Calendar.getInstance()
            val tk = dateFormat.format(cal.time)
            println("Time Display: $tk") // <-- I got result here
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
//                e.printStackTrace()
//            }
            return tk
        }

        fun checkAndRequestPermissions(context: Context): Boolean {
            val phonepermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
            val phonestatepermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
            val readcontactsLocation = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
            val writecontactspermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS)
            val callLogPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG)


            val listPermissionsNeeded = ArrayList<String>()

            if (phonepermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CALL_PHONE)
            }
            if (phonestatepermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE)
            }
            if (callLogPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG)
            }
            if (readcontactsLocation != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS)
            }
            if (writecontactspermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_CONTACTS)
            }
            if (listPermissionsNeeded.isNotEmpty()) {
                ActivityCompat.requestPermissions(context as Activity, listPermissionsNeeded.toTypedArray(), REQUEST_ID_MULTIPLE_PERMISSIONS)
                return false
            }
            return true
        }

    }

}