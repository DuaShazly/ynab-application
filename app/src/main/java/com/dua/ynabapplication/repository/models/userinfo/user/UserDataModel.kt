package com.dua.ynabapplication.repository.models.userinfo.user

import com.dua.ynabapplication.repository.models.userinfo.user.UserData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDataModel(
        @Json(name ="data")
        val userData: UserData
)