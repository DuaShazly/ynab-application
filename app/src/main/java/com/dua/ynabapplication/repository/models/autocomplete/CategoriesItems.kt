package com.dua.ynabapplication.repository.models.autocomplete

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesItems(
    val id: Long,
    val name: String
)