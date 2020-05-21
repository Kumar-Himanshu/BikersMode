package com.bikersmode

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bikersmode.utility.Utils

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (Utils.checkAndRequestPermissions(this)) {
            // carry on the normal flow, as the case of  permissions  granted.
            Handler().postDelayed({
                // This method will be executed once the timer is over
                // Start your app main activity

                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)

                // close this activity
                finish()
            }, SPLASH_TIME_OUT.toLong())
        }

    }

    companion object {
        private const val SPLASH_TIME_OUT = 2000
    }

}