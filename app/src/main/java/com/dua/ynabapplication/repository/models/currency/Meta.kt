package com.dua.ynabapplication.repository.models.currency

import com.dua.ynabapplication.repository.models.currency.Pagination
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
        val pagination: Pagination
)