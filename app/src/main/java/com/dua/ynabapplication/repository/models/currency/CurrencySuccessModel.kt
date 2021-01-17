package com.dua.ynabapplication.repository.models.currency

import com.dua.ynabapplication.repository.models.currency.CurrencyData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencySuccessModel(
        @Json(name ="data")
        val data: CurrencyData
)