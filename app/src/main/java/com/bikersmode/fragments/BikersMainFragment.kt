package com.bikersmode.fragments

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.bikersmode.R
import android.widget.Toast
import android.content.pm.PackageManager
import android.content.ComponentName
import android.widget.TextView
import com.bikersmode.braodcastreciever.CallBaring
import android.app.ActivityManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.bikersmode.MainActivity
import com.bikersmode.SplashActivity
import com.bikersmode.utility.Utils


/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * [BikersMainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BikersMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BikersMainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mPlusOneButton: Button? = null
    private var mHint: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_bikers_main, container, false)

        //Find the +1 button
        mPlusOneButton = view.findViewById<View>(R.id.plus_one_button) as Button
        mHint = view.findViewById<View>(R.id.tv_hint) as TextView

        return view
    }

    override fun onResume() {
        super.onResume()
        onButtonPressed()
        onButtonLongPressed()
    }

    // TODO: Rename button and start stop the mode
    private fun onButtonPressed() {
        mPlusOneButton?.setOnClickListener {
            if (checkAndRequestPermissions()) {
                if (mPlusOneButton?.text!!.toString() == getString(R.string.start)) {
                    Utils.Companion.isBackAllow = false
                    mPlusOneButton?.text = getString(R.string.stop)
                    enableBroadcastReceiver()
                    mHint?.text = getString(R.string.stop_text)

                }

            }
        }
    }

    private fun onButtonLongPressed() {
        mPlusOneButton?.setOnLongClickListener({
            if (mPlusOneButton?.text!!.toString() == getString(R.string.stop))
                mPlusOneButton?.text = getString(R.string.start)
            disableBroadcastReceiver()
            Utils.Companion.isBackAllow = true
            mHint?.text = getString(R.string.start_text)
            openLogListFragment()
            return@setOnLongClickListener true
        })
    }

    override fun onPause() {
        super.onPause()
        val activityManager = context
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        activityManager.moveTaskToFront(activity.taskId, 0)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BikersMainFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): BikersMainFragment {
            val fragment = BikersMainFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    private fun enableBroadcastReceiver() {

        val receiver = ComponentName(context, CallBaring::class.java)
        val pm = context?.packageManager

        pm?.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP)
        Toast.makeText(context, "Enabled broadcast receiver", Toast.LENGTH_SHORT).show()
    }

    /**
     * This method disables the Broadcast receiver registered in the AndroidManifest file.
     */
    private fun disableBroadcastReceiver() {
        val receiver = ComponentName(context, CallBaring::class.java)
        val pm = context?.packageManager
        pm?.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP)
        Toast.makeText(context, "Disabled broadcst receiver", Toast.LENGTH_SHORT).show()
    }

    private fun openLogListFragment() {
        activity.supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.details_fragment, CallLogListFragment.newInstance(10))
                .commitAllowingStateLoss()
    }

    private fun checkAndRequestPermissions(): Boolean {
        val phonepermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
        val phonestatepermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
        val readcontactsLocation = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
        val writecontactspermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS)


        val listPermissionsNeeded = ArrayList<String>()

        if (phonepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE)
        }
        if (phonestatepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (readcontactsLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS)
        }
        if (writecontactspermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CONTACTS)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toTypedArray(), SplashActivity.REQUEST_ID_MULTIPLE_PERMISSIONS)
            return false
        }
        return true
    }

}
