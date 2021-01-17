package com.dua.ynabapplication.data.remote.api

import com.dua.ynabapplication.repository.models.account.AccountSuccessModel
import com.dua.ynabapplication.repository.models.account.AccountsModel
import com.dua.ynabapplication.utils.Constants
import com.dua.ynabapplication.utils.Constants.Companion.ACCOUNTS_API_ENDPOINT
import com.dua.ynabapplication.utils.Constants.Companion.SINGLE_ACCOUNT_API_ENDPOINT
import retrofit2.Response
import retrofit2.http.*


interface AccountsService {

    @GET(ACCOUNTS_API_ENDPOINT)
    suspend fun getAccounts(@Path("budget_id") budget_id : String,
                            @Query("last_knowledge_of_server") last_knowledge_of_server: Int): Response<AccountsModel>

    @FormUrlEncoded
    @POST(ACCOUNTS_API_ENDPOINT)
    suspend fun createAccount(@Path("budget_id") budget_id:String,
        @Field("name") name: String,
                              @Field("type") type: String,
                              @Field("balance") balance: Long?): Response<AccountSuccessModel>

    @GET(SINGLE_ACCOUNT_API_ENDPOINT)
    suspend fun getsINGLEAccount(@Path("budget_id") budget_id : String,
                                 @Path("account_id ") account_id  : String): Response<AccountSuccessModel>

    @FormUrlEncoded
    @PUT("$ACCOUNTS_API_ENDPOINT/{accountId}")
    suspend fun updateAccount(@Path("accountId") accountId: Long,
                      @Field("name") name: String,
                      @Field("type") type: String,
                      @Field("currency_code") currencyCode: String?,
                      @Field("iban") iban: String?,
                      @Field("bic") bic: String?,
                      @Field("account_number") accountNumber: String?,
                      @Field("opening_balance") openingBalance: String?,
                      @Field("opening_balance_date") openingBalanceDate: String?,
                      @Field("account_role") accountRole: String?,
                      @Field("virtual_balance") virtualBalance: String?,
                      @Field("include_net_worth") includeNetWorth: Boolean,
                      @Field("notes") notes: String?,
                      @Field("liability_type") liabilityType: String?,
                      @Field("liability_amount") liabilityAmount: String?,
                      @Field("liability_start_date") liabilityStartDate: String?,
                      @Field("interest") interest: String?,
                      @Field("interest_period") interestPeriod: String?): Response<AccountSuccessModel>

}