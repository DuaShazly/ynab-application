package com.dua.ynabapplication.repository.models.budget.budgetList

import androidx.room.Entity
import androidx.room.Ignore
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class BudgetListAttributes(
        var active: Boolean?,
        var created_at: String?,
        var name: String,
        var order: Int?,
        @Ignore
        var spent: List<Spent> = listOf(),
        var updated_at: String?
){
        constructor() : this(true,"","",1, listOf(),"")
}