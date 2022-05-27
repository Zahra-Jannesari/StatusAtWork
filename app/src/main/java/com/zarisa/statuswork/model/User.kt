package com.zarisa.statuswork.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: String = "0",
    @Json(name = "name") val name: String,
    @Json(name = "avatar") val avatarUrl: String="a",
    @Json(name = "password") val password: Int,
    @Json(name = "status") var status: String="New User"
)

enum class ApiState { LOADING, DONE, ERROR, BAD_CONNECTION }

