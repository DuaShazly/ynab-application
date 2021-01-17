package com.dua.ynabapplication.repository.models.budget

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dua.ynabapplication.repository.models.budget.BudgetAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "budget")
data class BudgetData(
    @Embedded
    @Json(name = "budgets")
    val budgetAttributes: BudgetAttributes,
    @Json(name = "default_budget")
    val defultBudgetAttributes: DefaultBudgetAttributes,
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val budgetId: Long
)