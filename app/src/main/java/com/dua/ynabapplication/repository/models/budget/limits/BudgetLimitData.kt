package com.dua.ynabapplication.repository.models.budget.limits

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dua.ynabapplication.repository.models.budget.limits.BudgetLimitAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "budgetLimit")
data class BudgetLimitData(
        @Embedded
        val attributes: BudgetLimitAttributes,
        @PrimaryKey(autoGenerate = false)
        @Json(name ="id")
        val budgetLimitId: Long
)