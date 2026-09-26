package com.youtweet.models

data class VideoModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val creatorId: String = "",
    val creatorName: String = "",
    val videoUrl: String = "",
    val thumbnailUrl: String = "",
    val likes: Int = 0,
    val views: Int = 0,
    val createdAt: Long = 0L
)
