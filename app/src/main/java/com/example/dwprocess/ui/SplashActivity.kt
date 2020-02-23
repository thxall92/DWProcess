package com.example.dwprocess.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import com.example.dwprocess.MyAccountListActivity
import com.example.dwprocess.R
import com.example.dwprocess.base.BaseActivity
import com.example.dwprocess.databinding.ActivitySplashBinding
import org.jetbrains.anko.intentFor


class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    val DURATION:Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startNextActivity()
    }

    private fun startNextActivity() {
        val uriString = intent.dataString

        if (uriString.isNullOrBlank()) {
            Handler().postDelayed({
                startActivity(Intent(applicationContext, MyAccountListActivity::class.java))
                finish()
            },DURATION)
        } else {
            val uri = Uri.parse(uriString)

            if (uri.scheme.equals("test")) {

                if (uri != null) {

                    val addressIsPhoneType = uri.lastPathSegment.equals("contact")

                    val accountNumber = uri.getQueryParameter("accountNumber")
                    val amount = uri.getQueryParameter("amount")
                    val depositAddress = when (addressIsPhoneType) {
                        true -> uri.getQueryParameter("receiverPhoneNumber")
                        false -> uri.getQueryParameter("receiverAccountNumber")
                    }

                    startActivity(
                        applicationContext.intentFor<TransferActivity>(
                            TransferActivity.MY_ACCOUNT_NUMBER to accountNumber,
                            TransferActivity.DEPOSIT_ACCOUNT_NUMBER to depositAddress,
                            TransferActivity.AMOUNT to amount
                        )
                    )
                    finish()
                }
            }
        }
    }
}
