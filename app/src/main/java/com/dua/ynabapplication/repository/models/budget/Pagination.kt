package com.dua.ynabapplication.repository.models.budget

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pagination(
        val count: Int,
        val current_page: Int,
        val per_page: Int,
        val total: Int,
        val total_pages: Int
)