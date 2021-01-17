package com.dua.ynabapplication.repository.models.budget.limits

import com.dua.ynabapplication.repository.models.budget.limits.BudgetLimitData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetLimitModel(
        @Json(name ="data")
        val budgetLimitData: List<BudgetLimitData>
)