package com.dua.ynabapplication.repository.models.userinfo.system

import com.dua.ynabapplication.repository.models.userinfo.system.SystemData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SystemInfoModel(
        @Json(name ="data")
        val systemData: SystemData
)