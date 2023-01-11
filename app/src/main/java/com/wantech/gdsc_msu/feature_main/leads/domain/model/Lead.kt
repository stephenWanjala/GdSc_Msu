package com.wantech.gdsc_msu.feature_main.leads.domain.model

data class Lead(
    val name: String,
    val position: String,
    val technologies: List<String>
)
