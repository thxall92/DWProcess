package com.example.dwprocess.data.model

import com.google.gson.annotations.SerializedName

data class DepositContact(
    @SerializedName("name")
    val accountHolder : String,
    val phoneNumber: String
){
    fun toDepositAccountInfo() = DepositAccountInfo(accountHolder, phoneNumber, true, true)
}
