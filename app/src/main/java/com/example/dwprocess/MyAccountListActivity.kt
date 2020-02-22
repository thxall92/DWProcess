package com.example.dwprocess

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import com.example.dwprocess.base.BaseActivity
import com.example.dwprocess.base.BaseRecyclerViewAdapter
import com.example.dwprocess.base.BaseViewHolder
import com.example.dwprocess.databinding.ActivityMyAccountListBinding
import com.example.dwprocess.databinding.MyAccountItemBinding
import com.example.dwprocess.ui.AmountInputActivity
import com.example.dwprocess.viewmodel.AccountListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MyAccountListActivity : BaseActivity<ActivityMyAccountListBinding>(R.layout.activity_my_account_list) {

    private val accountListViewModel by viewModel<AccountListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            vm = accountListViewModel

            rvMyAccountList.adapter = object : BaseRecyclerViewAdapter<String, MyAccountItemBinding>(
                R.layout.my_account_item,
                BR.account
            ){
//                override fun onBindViewHolder(holder: BaseViewHolder<MyAccountItemBinding>, position: Int) {
//                    super.onBindViewHolder(holder, position)
//                    holder.binding.run{
//
//                    }
//                }
                override fun onBindViewHolder(holder: BaseViewHolder<MyAccountItemBinding>, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.binding.run {
                        btnTransfer.setOnClickListener{
                            startActivity(Intent(applicationContext, AmountInputActivity::class.java))
                        }
                    }
                }
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MyAccountItemBinding> {
                    return super.onCreateViewHolder(parent, viewType).apply {  }

                }
            }
        }

//        rvExchangeList.adapter = object : BaseRecyclerViewAdapter<String, ExchangeSelectItemBinding>(
//            layoutRes = R.layout.exchange_select_item,
//            bindingVariableId = BR.exchange
//        ) {
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ExchangeSelectItemBinding> {
//                return super.onCreateViewHolder(parent, viewType).apply {
//                    itemView.setOnClickListener {
//                        if (exchangeSelectViewModel.selectedItemPosition != adapterPosition) {
//                            val prevPosition = exchangeSelectViewModel.selectedItemPosition
//                            exchangeSelectViewModel.selectedItemPosition = adapterPosition
//                            exchangeSelectViewModel.saveMainExchange()
//                            notifyItemChanged(prevPosition)
//                            notifyItemChanged(adapterPosition)
//                            refreshPage()
//                        }
//                    }
//                }
//            }
//
//            override fun onBindViewHolder(holder: BaseViewHolder<ExchangeSelectItemBinding>, position: Int) {
//                super.onBindViewHolder(holder, position)
//                holder.binding.run {
//                    selectedPosition = exchangeSelectViewModel.selectedItemPosition
//                    itemPosition = holder.adapterPosition
//                }
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        accountListViewModel.getMyAccountList()
    }
}
