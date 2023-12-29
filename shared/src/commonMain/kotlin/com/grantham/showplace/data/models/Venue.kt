package com.grantham.showplace.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    val id: Int? = null,
    val name: String,
    val ageLimit: String?,
    val capacity: String?,
    val accessibility: String? = null,
    val url: String?
)
