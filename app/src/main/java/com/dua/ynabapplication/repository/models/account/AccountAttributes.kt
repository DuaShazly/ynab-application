package com.dua.ynabapplication.repository.models.account

import androidx.room.Entity
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@Entity
@JsonClass(generateAdapter = true)
data class AccountAttributes(
        val id: String,
        val name: String,
        val type: String,
        val on_budget: Boolean,
        val closed: Boolean,
        val note: String,
        val balance: Long,
        val cleared_balance: Long,
        val uncleared_balance: Long,
        val transfer_payee_id: String,
        val deleted: Boolean

)