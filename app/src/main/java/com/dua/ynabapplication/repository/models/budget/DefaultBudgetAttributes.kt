package com.dua.ynabapplication.repository.models.budget

import com.dua.ynabapplication.repository.models.account.AccountData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class DefaultBudgetAttributes(
        val id: String,
        val name: String,
        val last_modified_on: String,
        val first_month: String,
        val last_month: String,
        val formate : DateFormatAttributes,
        val currency_format :CurrencyFormatAttributes,
        val accounts :List<AccountData>,
        val currency_decimal_places: Int,
        val amount: BigDecimal,
        @Json(name ="start")
        val start_date: String,
        @Json(name ="end")
        val end_date: String
)