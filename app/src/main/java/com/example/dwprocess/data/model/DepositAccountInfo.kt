package com.example.dwprocess.data.model

data class DepositAccountInfo(
    val accountHolder: String,
    val depositAddress: String,
    val isEnabled: Boolean = true
)
