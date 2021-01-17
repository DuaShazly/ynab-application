package com.dua.ynabapplication.repository.models.transaction

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionSuccessModel(
    val data: TransactionData
)