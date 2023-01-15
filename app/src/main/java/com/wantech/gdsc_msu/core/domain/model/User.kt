package com.wantech.gdsc_msu.core.domain.model

data class User(
    val userId: String,
    val profilePictureUrl: String,
    val username: String,
    val description: String,
)

data class Profile(
    val userId: String,
    val profilePictureUrl: String,
    val username: String,
    val description: String,
    val bio:String
)
