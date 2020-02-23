package com.example.dwprocess.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.dwprocess.base.BaseViewModel
import com.example.dwprocess.data.model.DepositAccount
import com.example.dwprocess.data.model.DepositAccountInfo
import com.example.dwprocess.data.model.DepositContact
import com.example.dwprocess.ext.networkCommunication
import com.example.dwprocess.repository.DWProcessRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.function.BiFunction

class WithdrawAccountListViewModel(
    private val dwProcessRepository: DWProcessRepository
) : BaseViewModel() {

    val accountList = MutableLiveData<List<DepositAccountInfo>>().apply { value = ArrayList() }
    val contactList = MutableLiveData<List<DepositAccountInfo>>().apply { value = ArrayList() }

    var sortedAddressList = MutableLiveData<List<DepositAccountInfo>>()

    init {
        dwProcessRepository.getDepositAccountList()
            .networkCommunication()
            .doOnSuccess { listItem ->
                accountList.postValue(listItem.map{
                    it.toDepositAccountInfo()
                })
            }
            .ignoreElement()
    }

    fun sortAccountList() {

        val accountListFetch = dwProcessRepository.getDepositAccountList()
            .networkCommunication()
            .doOnSuccess { listItem ->
                accountList.postValue(listItem.map{
                    it.toDepositAccountInfo()
                })
            }
            .ignoreElement()

        val contactListFetch = dwProcessRepository.getDepositContactList()
            .networkCommunication()
            .doOnSuccess { listItem ->
                contactList.postValue(listItem.map{
                    it.toDepositAccountInfo()
                })
            }
            .ignoreElement()


        Completable.merge(listOf(accountListFetch, contactListFetch))
            .doOnComplete {
                Log.d("WithdrawAccountList", "finish fetching")
            }
            .subscribe {
                sortedAddressList.postValue(
                    accountList.value!!.union(contactList.value!!)?.sortedBy {
                        it.accountHolder
                    }
                )
            }

    }

}