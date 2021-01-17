package com.dua.ynabapplication.data.remote.api

import com.dua.ynabapplication.utils.Constants
import com.dua.ynabapplication.utils.Constants.Companion.AVAILABLE_BUDGET_API_ENDPOINT
import com.dua.ynabapplication.utils.Constants.Companion.SINGLE_BUDGET_API_ENDPOINT
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.dua.ynabapplication.repository.models.autocomplete.BudgetItems
import com.dua.ynabapplication.repository.models.budget.BudgetData
import com.dua.ynabapplication.repository.models.budget.BudgetModel


interface BudgetService {

    @GET(AVAILABLE_BUDGET_API_ENDPOINT)
    suspend fun getAllBudget(@Query("boolean") page: Boolean): Response<BudgetModel>



    @GET(SINGLE_BUDGET_API_ENDPOINT)
    fun getSingleBudget(@Query("last_knowledge_of_server") page: Int,@Path("budget_id") id:String): Response<BudgetData>




}