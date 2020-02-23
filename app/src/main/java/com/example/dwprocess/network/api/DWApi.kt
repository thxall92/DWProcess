package com.example.dwprocess.network.api

import com.example.dwprocess.data.model.DepositAccount
import com.example.dwprocess.data.model.DepositContact
import com.example.dwprocess.data.model.MyAccount
import io.reactivex.Single
import retrofit2.http.*

interface DWApi {

    @GET("withdraw-account-list")
    fun getWithdrawAccountList(): Single<List<MyAccount>>

    @GET("deposit-account-list")
    fun getDepositAccountList(): Single<List<DepositAccount>>

    @GET("deposit-contact-list")
    fun getDepositContactList(): Single<List<DepositContact>>

    @FormUrlEncoded
    @POST("transfer/account")
    fun transferAccount(@Field("accountNumber") accountNumber: String,
                        @Field("amount") amount: Int,
                        @Field("receiverAccountNumber") receiverAccountNumber: String): Single<Any>

//    @FormUrlEncoded
//    @POST("transfer/contact")
//    fun transferContact(@Field("accountNumber") accountNumber: String,
//                        @Field("amount") amount: Int,
//                        @Field("receiverPhoneNumber") receiverPhoneNumber: String): Single<Map<String, Any>>
}