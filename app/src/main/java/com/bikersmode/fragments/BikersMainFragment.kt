package com.bikersmode.fragments

import android.content.Context
import android.os.Bundle
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
import android.content.Intent
import androidx.fragment.app.Fragment
import com.bikersmode.RouteActivity
import com.bikersmode.utility.Utils


/**
 * A fragment with a Google +1 button.
 * Use the [BikersMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BikersMainFragment : Fragment(), View.OnClickListener, View.OnLongClickListener {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mPlusOneButton: Button? = null
    private var mPlusTwoButton: Button? = null
    private var mHint: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bikers_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }


    override fun onPause() {
        super.onPause()
        val activityManager = context!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.moveTaskToFront(activity!!.taskId, 0)
    }


    companion object {
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

        val receiver = ComponentName(context!!, CallBaring::class.java)
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
        val receiver = ComponentName(context!!, CallBaring::class.java)
        val pm = context?.packageManager
        pm?.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP)
        Toast.makeText(context, "Disabled broadcst receiver", Toast.LENGTH_SHORT).show()
    }

    private fun openLogListFragment() {
        activity!!.supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.details_fragment, CallLogListFragment.newInstance(10))
                .commitAllowingStateLoss()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.plus_one_button -> {
                if (Utils.checkAndRequestPermissions(context!!)) {
                    if (mPlusOneButton?.text!!.toString() == getString(R.string.start)) {
                        Utils.isBackAllow = false
                        mPlusOneButton?.text = getString(R.string.stop)
                        enableBroadcastReceiver()
                        mHint?.text = getString(R.string.stop_text)

                    }

                }

            }
            R.id.plus_two_button -> {
                if (Utils.checkAndRequestPermissions(context!!)) {
                    val i = Intent(activity, RouteActivity::class.java)
                    startActivity(i)
                }

            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        var isLongClicked = false
        when (v!!.id) {
            R.id.plus_one_button -> {
                isLongClicked = true
                if (mPlusOneButton?.text!!.toString() == getString(R.string.stop))
                    mPlusOneButton?.text = getString(R.string.start)
                disableBroadcastReceiver()
                Utils.isBackAllow = true
                mHint?.text = getString(R.string.start_text)
                openLogListFragment()
            }
        }
        return isLongClicked
    }

    private fun initUi(){
        //Find the +1 button
        mPlusOneButton = view!!.findViewById<View>(R.id.plus_one_button) as Button
        mPlusTwoButton = view!!.findViewById<View>(R.id.plus_two_button) as Button
        mHint = view!!.findViewById<View>(R.id.tv_hint) as TextView

        mPlusTwoButton?.setOnClickListener(this)
        mPlusOneButton?.setOnClickListener(this)
        mPlusOneButton?.setOnLongClickListener(this)

    }

}
