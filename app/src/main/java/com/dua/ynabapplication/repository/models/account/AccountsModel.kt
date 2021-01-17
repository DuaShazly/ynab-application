package com.dua.ynabapplication.repository.models.account

import androidx.room.Embedded
import androidx.room.Entity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class AccountsModel(
    @Embedded
        val data: List<AccountData>,
    val server_knowledge: Int
)