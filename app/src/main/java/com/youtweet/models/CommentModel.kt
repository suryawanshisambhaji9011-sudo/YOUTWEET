package com.youtweet.models

data class CommentModel(
    val id: String = "",
    val videoId: String = "",
    val userId: String = "",
    val userName: String = "",
    val text: String = "",
    val createdAt: Long = 0L
)
