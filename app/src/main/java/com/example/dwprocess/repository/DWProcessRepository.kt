package com.example.dwprocess.repository

import com.example.dwprocess.network.api.DWApi

class DWProcessRepository(private val api: DWApi) {

    fun getWithdrawAccountList() = api.getWithdrawAccountList()

    fun getDepositAccountList() = api.getDepositAccountList()

    fun getDepositContactList() = api.getDepositContactList()

//    fun transferAccount(accountNumber: String, amount: Int, receiverAccountNumber: String): Single<String> {
//        return api.transferAccount(accountNumber, amount, receiverAccountNumber)
//            .map { it.orderId }
//            .doOnSuccess { updateOrder(true) }
//    }
//
//    fun transferContact(accountNumber: String, amount: Int, receiverPhoneNumber: String): Single<String> {
//        return api.transferContact(accountNumber, amount, receiverPhoneNumber)
//            .map { it.orderId }
//            .doOnSuccess { updateOrder(true) }
//    }

}