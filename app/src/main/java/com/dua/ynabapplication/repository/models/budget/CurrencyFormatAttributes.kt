package com.dua.ynabapplication.repository.models.budget

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class CurrencyFormatAttributes(
        val iso_code: String,
        val example_format :String,
        val decimal_digits :Int,
        val decimal_separator:String,
        val symbol_first :Boolean,
        val group_separator :String,
        val currency_symbol :String,
        val display_symbol :Boolean
)