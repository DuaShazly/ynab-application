package com.dua.ynabapplication.repository.models.account

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dua.ynabapplication.repository.models.account.AccountAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "accounts")
data class AccountData(
        @PrimaryKey(autoGenerate = false)
        @Json(name ="id")
        val accountId: String = "",
        @Embedded
        @Json(name ="attributes")
        val accountAttributes: AccountAttributes
)