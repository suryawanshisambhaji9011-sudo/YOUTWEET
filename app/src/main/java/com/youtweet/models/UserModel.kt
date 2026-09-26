package com.youtweet.models

data class UserModel(
    val uid: String = "",
    val name: String = "",
    val bio: String = "",
    val profileImage: String = "",
    val followers: Int = 0,
    val following: Int = 0
)
