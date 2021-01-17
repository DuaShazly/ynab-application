package com.dua.ynabapplication.repository.models.transaction

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionAttributes(
        val id: String,
        val date: String,
        val amount :Int,
        val cleared :String,
        val approved:Boolean,
        val flag_color:String,
        val account_id :String,
        val payee_id: String,
        val category_id: String,
        val transfer_account_id: String,
        val transfer_transaction_id : String,
        val matched_transaction_id: String,
        val import_id: String,
        val deleted: Boolean,
        val account_name : String,
        val payee_name : String,
        val category_name : String,
        val transactions: List<Transactions>,
        val updated_at: String,
        val user: Int
)