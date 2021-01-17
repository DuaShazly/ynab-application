package com.dua.ynabapplication.repository.models.budget.budgetList

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
        val pagination: Pagination
)