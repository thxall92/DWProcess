package com.example.dwprocess.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.dwprocess.base.BaseViewModel
import com.example.dwprocess.data.model.MyAccount
import com.example.dwprocess.ext.networkCommunication
import com.example.dwprocess.repository.DWProcessRepository

class AccountListViewModel(
    private val dwProcessRepository: DWProcessRepository
) : BaseViewModel() {

    val myAccountList = MutableLiveData<List<MyAccount>>()

    fun getMyAccountList(){
        dwProcessRepository.getWithdrawAccountList()
            .networkCommunication()
            .subscribe { items -> myAccountList.postValue(items) }
    }

}