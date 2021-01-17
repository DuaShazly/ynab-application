package com.dua.ynabapplication.repository.models.budget

import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetModel(
    @Embedded
        @Json(name ="data")
        val budgetData: List<BudgetData>,
    val server_knowledge: Int
)