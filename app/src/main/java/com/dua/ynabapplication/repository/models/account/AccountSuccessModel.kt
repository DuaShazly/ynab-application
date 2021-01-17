package com.dua.ynabapplication.repository.models.account

import com.dua.ynabapplication.repository.models.account.AccountData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountSuccessModel(
        @Json(name ="data")
        val data: AccountData
)