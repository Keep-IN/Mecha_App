package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserList (
    val status: Int,
    val user: List<UserModel> = listOf()
)