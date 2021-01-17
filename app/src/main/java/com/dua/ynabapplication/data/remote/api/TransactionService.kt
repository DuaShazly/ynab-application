package com.dua.ynabapplication.data.remote.api

import com.dua.ynabapplication.repository.models.transaction.TransactionModel
import com.dua.ynabapplication.repository.models.transaction.TransactionSuccessModel
import com.dua.ynabapplication.repository.models.transaction.Transactions
import com.dua.ynabapplication.utils.Constants
import com.dua.ynabapplication.utils.Constants.Companion.TRANSACTION_API_ENDPOINT
import retrofit2.Response
import retrofit2.http.*

interface TransactionService {

    @GET(TRANSACTION_API_ENDPOINT)
    suspend fun getTransactions(
        @Path("budget_id") budget_id: String?,
        @Query("since_date") since_date: String?,
        @Query("type") type: String,
        @Query("last_knowledge_of_server") last_knowledge_of_server: Int
    ): Response<TransactionModel>


    @GET(TRANSACTION_API_ENDPOINT)
    suspend fun getSingleTransactions(
        @Path("budget_id") budget_id: String?,
        @Path("transaction_id") transaction_id: String?
    ): Response<Transactions>


}