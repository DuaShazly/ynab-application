package com.dua.ynabapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dua.ynabapplication.repository.models.budget.budgetList.BudgetListData
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BudgetListDataDao: BaseDao<BudgetListData> {

    @Query("DELETE FROM budget_list")
    abstract suspend fun deleteAllBudgetList(): Int

    @Query("SELECT budget_list.name, budget_list.budgetListId FROM budget_list JOIN budgetListFts ON (budget_list.budgetListId = budgetListFts.budgetListId) WHERE budgetListFts MATCH :budgetName")
    abstract suspend fun searchBudgetName(budgetName: String): List<BudgetListData>

    @Query("SELECT distinct budget_list.name FROM budget_list JOIN budgetListFts ON (budget_list.budgetListId = budgetListFts.budgetListId)")
    abstract fun getAllBudgetName(): Flow<List<String>>

}