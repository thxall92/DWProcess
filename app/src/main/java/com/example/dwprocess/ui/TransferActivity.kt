package com.example.dwprocess.ui

import android.os.Bundle
import com.example.dwprocess.R
import com.example.dwprocess.base.BaseActivity
import com.example.dwprocess.databinding.ActivityTransferBinding
import com.example.dwprocess.repository.DWProcessRepository
import org.koin.android.ext.android.inject

class TransferActivity : BaseActivity<ActivityTransferBinding>(R.layout.activity_transfer) {

    companion object {
        const val MY_ACCOUNT_NUMBER = "MY_ACCOUNT_NUMBER"
        const val DEPOSIT_ACCOUNT_HOLDER = "DEPOSIT_ACCOUNT_HOLDER"
        const val DEPOSIT_ACCOUNT_NUMBER = "DEPOSIT_ACCOUNT_NUMBER"
        const val AMOUNT = "AMOUNT"
        const val IS_PHONE_ADRESS_TYPE = "IS_PHONE_ADRESS_TYPE"
    }

    private var myAccountNumber: String = ""
    private var depositAccountHolder: String = ""
    private var depositAccountNumber: String = ""
    private var isPhoneAddressType: Boolean = false
    private var amount: Int = 0

    private val dwProcessRepository by inject<DWProcessRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAccountNumber = intent.getStringExtra(MY_ACCOUNT_NUMBER)!!
        if (intent.hasExtra(DEPOSIT_ACCOUNT_HOLDER)) {
            depositAccountHolder = intent.getStringExtra(DEPOSIT_ACCOUNT_HOLDER)!!
        }

        depositAccountNumber = intent.getStringExtra(DEPOSIT_ACCOUNT_NUMBER)!!
        isPhoneAddressType = intent.getBooleanExtra(IS_PHONE_ADRESS_TYPE, false)
        amount = intent.getStringExtra(AMOUNT)!!.toInt()

        binding.run {
            view = this@TransferActivity


            val transferDesc = when (depositAccountHolder == "") {
                true -> "${depositAccountNumber}로 \n${amount}원을 송금합니다."
                false -> "$depositAccountHolder($depositAccountNumber)님께 \n${amount}원을 송금합니다."
            }
            tvTransferDesc.text = transferDesc
        }
    }

    fun handleTransfer() {
        if(isPhoneAddressType){
            dwProcessRepository.transferContact(myAccountNumber, amount, depositAccountNumber)
        }else{
            dwProcessRepository.transferAccount(myAccountNumber, amount, depositAccountNumber)
        }
    }
}
