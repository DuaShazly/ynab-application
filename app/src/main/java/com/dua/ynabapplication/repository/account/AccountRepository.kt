package com.dua.ynabapplication.repository.account

import com.dua.ynabapplication.data.local.dao.AccountsDataDao
import com.dua.ynabapplication.data.local.dao.TransactionDataDao
import com.dua.ynabapplication.data.remote.api.AccountsService
import com.dua.ynabapplication.repository.models.ApiResponses
import com.dua.ynabapplication.repository.models.account.AccountData
import com.dua.ynabapplication.repository.models.account.AccountSuccessModel
import com.dua.ynabapplication.repository.models.error.ErrorModel
import com.dua.ynabapplication.utils.extension.debounce
import com.dua.ynabapplication.utils.network.HttpConstants
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Response

@Suppress("RedundantSuspendModifier")
class AccountRepository(private val accountDao: AccountsDataDao,
                        private val accountsService: AccountsService
){

    @Throws(Exception::class)
    suspend fun authViaPat(): Boolean{
        val networkCall = accountsService.getAccounts("", 1)
        val responseBody = networkCall.body()
        return responseBody != null && networkCall.isSuccessful
    }



    suspend fun getTransactionByAccountId(accountId: Long, startDate: String,
                                          endDate: String, type: String,
                                          transactionDao: TransactionDataDao
    ){
        try {
//            val networkCall = accountsService.getTransactionsByAccountId(accountId, 1, startDate, endDate, type)
//            val responseBody = networkCall.body()
//            if(responseBody != null && networkCall.isSuccessful){
//                responseBody.data.forEach { transactionData ->
//                    transactionData.transactionAttributes.transactions.forEach { transactions ->
//                        transactionDao.insert(transactions)
//                        transactionDao.insert(TransactionIndex(
//                                0,
//                                transactionData.transactionId,
//                                transactions.transaction_journal_id,
//                                transactionData.transactionAttributes.group_title
//                        ))
//                    }
//                }
//                val pagination = responseBody.meta.pagination
//                if (pagination.total_pages != pagination.current_page) {
//                    for (items in 2..pagination.total_pages) {
//                        accountsService.getTransactionsByAccountId(accountId, items, startDate, endDate, "all")
//                    }
//                }
//            }
        } catch (exception: Exception){ }
    }


    suspend fun createAccount(budget_id: String, name: String,
                           type: String, balance: Long): ApiResponses<AccountSuccessModel> {
        return try {
            val networkCall = accountsService.createAccount(budget_id, name, type, balance)
            parseResponse(networkCall)
        } catch (exception: Exception){
            ApiResponses(error = exception)
        }
    }

    suspend fun updateAccount(accountId: Long, accountName: String, accountType: String,
                      currencyCode: String?, iban: String?, bic: String?, accountNumber: String?,
                      openingBalance: String?, openingBalanceDate: String?, accountRole: String?,
                      virtualBalance: String?, includeInNetWorth: Boolean, notes: String?, liabilityType: String?,
                      liabilityAmount: String?, liabilityStartDate: String?, interest: String?, interestPeriod: String?): ApiResponses<AccountSuccessModel> {
        return try {
            val networkCall = accountsService.updateAccount(accountId, accountName, accountType, currencyCode, iban, bic, accountNumber,
                    openingBalance, openingBalanceDate, accountRole, virtualBalance, includeInNetWorth,
                    notes, liabilityType, liabilityAmount, liabilityStartDate, interest, interestPeriod)
            parseResponse(networkCall)
        } catch (exception: Exception){
            ApiResponses(error = exception)
        }
    }


    private suspend fun parseResponse(responseFromServer: Response<AccountSuccessModel>): ApiResponses<AccountSuccessModel> {
        val responseBody = responseFromServer.body()
        val responseErrorBody = responseFromServer.errorBody()
        if(responseBody != null && responseFromServer.isSuccessful){
            accountDao.insert(responseBody.data)
            return ApiResponses(response = responseBody)
        } else {
            if(responseErrorBody != null){
                // Ignore lint warning. False positive
                // https://github.com/square/retrofit/issues/3255#issuecomment-557734546
                val moshi = Moshi.Builder().build().adapter(ErrorModel::class.java).fromJson(responseErrorBody.source())
                val errorMessage = when {
                    moshi?.errors?.name != null -> moshi.errors.name[0]
                    moshi?.errors?.account_number != null -> moshi.errors.account_number[0]
                    moshi?.errors?.interest != null -> moshi.errors.interest[0]
                    moshi?.errors?.liabilityStartDate != null -> moshi.errors.liabilityStartDate[0]
                    moshi?.errors?.currency_code != null -> moshi.errors.currency_code[0]
                    moshi?.errors?.iban != null -> moshi.errors.iban[0]
                    moshi?.errors?.bic != null -> moshi.errors.bic[0]
                    moshi?.errors?.opening_balance != null -> moshi.errors.opening_balance[0]
                    moshi?.errors?.opening_balance_date != null -> moshi.errors.opening_balance_date[0]
                    moshi?.errors?.interest_period != null -> moshi.errors.interest_period[0]
                    moshi?.errors?.liability_amount != null -> moshi.errors.liability_amount[0]
                    moshi?.errors?.exception != null -> moshi.errors.exception[0]
                    else -> moshi?.message ?: "Error occurred while saving Account"
                }
                return ApiResponses(errorMessage = errorMessage)
            }
            return ApiResponses(errorMessage = "Error occurred while saving Account")
        }
    }

    private suspend fun deleteAccountByType(accountType: String): Int = accountDao.deleteAccountByType(accountType)

    @Deprecated("This is a very expensive network call. Use getAccountByNameAndType() instead")
    private suspend fun loadRemoteData(budget_id: String){
        val accountData: MutableList<AccountData> = arrayListOf()
        try {
            val networkCall = accountsService.getAccounts(budget_id, 1)
            accountData.addAll(networkCall.body()?.data?.toMutableList() ?: arrayListOf())
            val responseBody = networkCall.body()
            if (responseBody != null && networkCall.isSuccessful) {

                        accountData.addAll(responseBody.data)



                accountData.forEach { data ->
                    accountDao.insert(data)
                }
            }
        } catch (exception: Exception){ }
    }
}