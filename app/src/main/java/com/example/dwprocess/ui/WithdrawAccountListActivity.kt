package com.example.dwprocess.ui

import android.os.Bundle
import android.view.ViewGroup
import com.example.dwprocess.BR
import com.example.dwprocess.R
import com.example.dwprocess.base.BaseActivity
import com.example.dwprocess.base.BaseRecyclerViewAdapter
import com.example.dwprocess.base.BaseViewHolder
import com.example.dwprocess.databinding.ActivityWithdrawAccountListBinding
import com.example.dwprocess.databinding.DepositAccountItemBinding
import com.example.dwprocess.databinding.MyAccountItemBinding
import com.example.dwprocess.viewmodel.WithdrawAccountListViewModel
import org.jetbrains.anko.intentFor
import org.koin.android.viewmodel.ext.android.viewModel

class WithdrawAccountListActivity :
    BaseActivity<ActivityWithdrawAccountListBinding>(R.layout.activity_withdraw_account_list) {

    private val withdrawAccountListViewModel by viewModel<WithdrawAccountListViewModel>()

    companion object {
        const val MY_ACCOUNT_NUMBER = "MY_ACCOUNT_NUMBER"
        const val AMOUNT = "AMOUNT"
    }

    private var myAccountNumber: String = ""
    private var amount: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAccountNumber = intent.getStringExtra(MY_ACCOUNT_NUMBER)!!
        amount = intent.getStringExtra(AMOUNT)!!

        binding.run {
            vm = withdrawAccountListViewModel
            rvDeposiAccountList.adapter =
                object : BaseRecyclerViewAdapter<String, DepositAccountItemBinding>(
                    R.layout.deposit_account_item,
                    BR.account
                ) {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                            : BaseViewHolder<DepositAccountItemBinding> {
                        return super.onCreateViewHolder(parent, viewType).apply {
                            itemView.setOnClickListener {
                                startActivity(
                                    applicationContext.intentFor<TransferActivity>(
                                        TransferActivity.MY_ACCOUNT_NUMBER to myAccountNumber,
                                        TransferActivity.DEPOSIT_ACCOUNT_HOLDER to binding.account!!.accountHolder,
                                        TransferActivity.DEPOSIT_ACCOUNT_NUMBER to binding.account!!.depositAddress,
                                        TransferActivity.AMOUNT to amount
                                    )
                                )
                            }
                        }

                    }
                }
        }

    }

    override fun onResume() {
        super.onResume()

        withdrawAccountListViewModel.sortAccountList()
    }
}
