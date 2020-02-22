package com.example.dwprocess.data.model

data class MyAccount(
    val accountNumber: String,
    val balance: Int,
    val bankLogo: String
){
    fun getBalanceText() = "${balance}원"
}
