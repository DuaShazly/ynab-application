package com.dua.ynabapplication.repository.models.budget

import androidx.annotation.WorkerThread
import com.dua.ynabapplication.data.local.dao.BudgetDataDao
import com.dua.ynabapplication.data.local.dao.BudgetLimitDao
import com.dua.ynabapplication.data.local.dao.BudgetListDataDao
import com.dua.ynabapplication.data.local.dao.SpentDataDao
import com.dua.ynabapplication.data.remote.api.BudgetService
import com.dua.ynabapplication.repository.models.budget.budgetList.BudgetListData
import com.dua.ynabapplication.utils.network.retrofitCallback
import kotlinx.coroutines.flow.Flow

import java.math.BigDecimal

@Suppress("RedundantSuspendModifier")
@WorkerThread
class BudgetRepository(private val budget: BudgetDataDao,
                       private val budgetList: BudgetListDataDao,
                       private val spentDao: SpentDataDao,
                       private val budgetLimitDao: BudgetLimitDao,
                       private val budgetService: BudgetService
) {

    suspend fun insertBudget(budgetData: BudgetData){
        budget.insert(budgetData)
    }

    suspend fun deleteAllBudget() = budget.deleteAllBudget()

    suspend fun insertBudgetList(budgetData: BudgetListData){
        budgetList.insert(budgetData)
        val spentList = budgetData.budgetListAttributes.spent
        if(spentList.isNotEmpty()){
            spentList.forEach { spent ->
                spent.spentId = budgetData.budgetListId
                spentDao.insert(spent)
            }
        }
    }


    suspend fun deleteBudgetList() = budgetList.deleteAllBudgetList()

    suspend fun retrieveConstraintBudgetWithCurrency(startDate: String, endDate: String,
                                                     currencyCode: String) =
            budget.getConstraintBudgetWithCurrency(startDate, endDate, currencyCode)


    suspend fun getAllBudget(){
        try {
            val availableBudget: MutableList<BudgetData> = arrayListOf()
            val networkCall = budgetService.getAllBudget(true)
            val responseBody = networkCall.body()
            if(responseBody != null && networkCall.isSuccessful){
                deleteAllBudget()
                val networkData = networkCall.body()
                if (networkData?.budgetData != null) {
                    networkData?.budgetData?.forEach {
                            budgetList ->
                        availableBudget.add(budgetList)
                        }
                    }
                }

                availableBudget.forEach { budgetData ->
                    insertBudget(budgetData)
                }

        } catch (exception: Exception){ }
    }


    suspend fun getSingBudget(){
        try {
            val availableBudget: BudgetData
            val networkCall = budgetService.getSingleBudget(0,"")
            val responseBody = networkCall.body()
            if(responseBody != null && networkCall.isSuccessful){
                deleteAllBudget()
                val networkData = networkCall.body()
                if (networkData != null) {

                    availableBudget = networkData

                    insertBudget(availableBudget)
                }
                }





        } catch (exception: Exception){ }
    }
}