package com.bikersmode.braodcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.bikersmode.database.UserModel
import com.bikersmode.database.UsersDBHelper
import com.bikersmode.utility.Utils

class CallBaring : BroadcastReceiver() {
//    private var number: String? = null
//    private var name: String? = null
//    private var time: String? = null
    lateinit var usersDBHelper : UsersDBHelper
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "android.intent.action.PHONE_STATE")
            return
        else
        {
            usersDBHelper = UsersDBHelper(context)
            // Fetch the number of incoming call
//            number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
//            name = Utils.Companion.getContactName(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER),context)
//            time = Utils.Companion.getContactTime()
            disconnectPhoneItelephony(context,intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER))
            usersDBHelper.insertUser(UserModel(mobileNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER),name = Utils.Companion.getContactName(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER),context),time = Utils.Companion.getContactTime()))
            return
        }
    }

    private fun disconnectPhoneItelephony(context: Context?, mobileNumber: String) {
        val telephonyService: Any
        val telephony = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
            var c = Class.forName(telephony.javaClass.name)
            var m = c.getDeclaredMethod("getITelephony")
            m.isAccessible = true
            telephonyService = m.invoke(telephony) as Any
            c = Class.forName(telephonyService.javaClass.name) // Get its class
            m = c.getDeclaredMethod("endCall") // Get the "endCall()" method
            m.isAccessible = true // Make it accessible
            m.invoke(telephonyService) // invoke endCall()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
