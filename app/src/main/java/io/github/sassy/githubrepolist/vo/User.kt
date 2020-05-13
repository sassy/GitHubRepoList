package io.github.sassy.githubrepolist.vo

import com.google.gson.annotations.SerializedName

data class User (
    val id: Int,
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String
)
