package com.wantech.gdsc_msu.feature_main.news.domain.model

data class GroupNewsItemModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val dateTime:Long,
    val groupLink: String,
)
