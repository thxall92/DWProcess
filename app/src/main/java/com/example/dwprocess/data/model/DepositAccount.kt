package com.example.dwprocess.data.model

data class DepositAccount(
    val accountHolder: String,
    val accountNumber: String,
    val isEnabled: Boolean
){
   fun toDepositAccountInfo() = DepositAccountInfo(accountHolder, accountNumber, isEnabled, false)
}