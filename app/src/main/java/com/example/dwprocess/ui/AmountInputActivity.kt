package com.example.dwprocess.ui

import android.os.Bundle
import com.example.dwprocess.R
import com.example.dwprocess.base.BaseActivity
import com.example.dwprocess.databinding.ActivityAmountInputBinding
import org.jetbrains.anko.intentFor

class AmountInputActivity  : BaseActivity<ActivityAmountInputBinding>(R.layout.activity_amount_input) {

    companion object {
        const val MY_ACCOUNT_NUMBER = "MY_ACCOUNT_NUMBER"
        const val BALANCE = "BALANCE"
    }

    private var myAccountNumber : String = ""
    private var balance : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAccountNumber = intent.getStringExtra(MY_ACCOUNT_NUMBER)!!
        balance = intent.getIntExtra(BALANCE, 0)

        binding.run{
            view = this@AmountInputActivity
        }
    }

    fun completeInputAmount(){
        val amount = binding.edtAmount.text.toString()

        startActivity(
            applicationContext.intentFor<WithdrawAccountListActivity>(
                WithdrawAccountListActivity.MY_ACCOUNT_NUMBER to myAccountNumber,
                WithdrawAccountListActivity.AMOUNT to amount
            )
        )
    }
}
