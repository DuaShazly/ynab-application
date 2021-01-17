package com.dua.ynabapplication.repository.models.currency

import com.dua.ynabapplication.repository.models.currency.CurrencyData
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DefaultCurrencyModel(
        val data: CurrencyData
)