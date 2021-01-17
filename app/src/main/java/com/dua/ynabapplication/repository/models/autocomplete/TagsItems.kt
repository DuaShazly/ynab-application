package com.dua.ynabapplication.repository.models.autocomplete

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagsItems(
        val id: Long,
        val name: String,
        val tag: String
)