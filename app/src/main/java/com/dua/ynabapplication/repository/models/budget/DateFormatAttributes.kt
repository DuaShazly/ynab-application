package com.dua.ynabapplication.repository.models.budget

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class DateFormatAttributes(
        val format: String
)