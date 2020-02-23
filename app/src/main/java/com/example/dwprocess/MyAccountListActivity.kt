package com.example.dwprocess

import android.os.Bundle
import android.view.ViewGroup
import com.example.dwprocess.base.BaseActivity
import com.example.dwprocess.base.BaseRecyclerViewAdapter
import com.example.dwprocess.base.BaseViewHolder
import com.example.dwprocess.databinding.ActivityMyAccountListBinding
import com.example.dwprocess.databinding.MyAccountItemBinding
import com.example.dwprocess.ui.AmountInputActivity
import com.example.dwprocess.viewmodel.AccountListViewModel
import org.jetbrains.anko.intentFor
import org.koin.android.viewmodel.ext.android.viewModel

class MyAccountListActivity :
    BaseActivity<ActivityMyAccountListBinding>(R.layout.activity_my_account_list) {

    private val accountListViewModel by viewModel<AccountListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            vm = accountListViewModel

            rvMyAccountList.adapter =
                object : BaseRecyclerViewAdapter<String, MyAccountItemBinding>(
                    R.layout.my_account_item,
                    BR.account
                ) {
                    override fun onCreateViewHolder(
                        parent: ViewGroup,
                        viewType: Int
                    ): BaseViewHolder<MyAccountItemBinding> {
                        return super.onCreateViewHolder(parent, viewType).apply {
                            binding.btnTransfer.setOnClickListener {
                                startActivity(
                                    applicationContext.intentFor<AmountInputActivity>(
                                        AmountInputActivity.MY_ACCOUNT_NUMBER to binding.account!!.accountNumber,
                                        AmountInputActivity.BALANCE to binding.account!!.balance
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
        accountListViewModel.getMyAccountList()
    }
}
