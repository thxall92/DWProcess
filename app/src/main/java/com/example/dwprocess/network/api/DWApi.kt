package com.example.dwprocess.network.api

import com.example.dwprocess.data.model.MyAccount
import io.reactivex.Single
import retrofit2.http.*

interface DWApi {

    @GET("withdraw-account-list")
    fun getWithdrawAccountList(): Single<List<MyAccount>>

//    @GET("deposit-account-list")
//    fun getDepositAccountList(): Single<Map<String, Any>>
//
//    @GET("deposit-contact-list")
//    fun getDepositContactList(): Single<Map<String, Any>>
//
//    @FormUrlEncoded
//    @POST("transfer/account")
//    fun transferAccount(@Field("accountNumber") accountNumber: String,
//                        @Field("amount") amount: Int,
//                        @Field("receiverAccountNumber") receiverAccountNumber: String): Single<Map<String, Any>>
//
//    @FormUrlEncoded
//    @POST("transfer/contact")
//    fun transferContact(@Field("accountNumber") accountNumber: String,
//                        @Field("amount") amount: Int,
//                        @Field("receiverPhoneNumber") receiverPhoneNumber: String): Single<Map<String, Any>>
}