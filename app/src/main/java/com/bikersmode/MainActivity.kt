package com.bikersmode

import android.os.Bundle
import com.bikersmode.fragments.BikersMainFragment


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        openFragment()
    }

    private fun openFragment() {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.details_fragment, BikersMainFragment.newInstance("", ""))
                .commitAllowingStateLoss()
    }
}
