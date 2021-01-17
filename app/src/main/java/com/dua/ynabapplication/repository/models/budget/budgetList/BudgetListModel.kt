package com.dua.ynabapplication.repository.models.budget.budgetList

import androidx.room.Embedded
import com.dua.ynabapplication.repository.models.budget.budgetList.BudgetListData
import com.dua.ynabapplication.repository.models.budget.budgetList.Meta
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetListModel(
    @Embedded
        val data: List<BudgetListData>,
    val meta: Meta
)