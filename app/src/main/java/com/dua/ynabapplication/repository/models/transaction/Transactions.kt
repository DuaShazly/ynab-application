package com.dua.ynabapplication.repository.models.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.OffsetDateTime

@JsonClass(generateAdapter = true)
@Entity(tableName = "transactionTable")
data class Transactions(
        @PrimaryKey(autoGenerate = false)
        val id: String,
        val transaction_id: String,
        val amount: Long,
        val memo: String,
        val payee_id: String,
        val payee_name: String,
        val category_id: String,
        val category_name: String,
        val transfer_account_id: String,
        val transfer_transaction_id: String,
        val deleted: Boolean
)