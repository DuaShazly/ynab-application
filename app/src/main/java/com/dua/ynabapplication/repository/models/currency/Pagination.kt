package com.dua.ynabapplication.repository.models.currency

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pagination(
        val total: Int,
        val count: Int,
        val per_page: Int,
        val current_page: Int,
        val total_pages: Int
)