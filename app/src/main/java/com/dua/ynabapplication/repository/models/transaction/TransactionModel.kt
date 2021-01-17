package com.dua.ynabapplication.repository.models.transaction

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionModel(
        val data: List<TransactionData>,
        val server_knowledge: Int
)