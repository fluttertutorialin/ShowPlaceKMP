package com.grantham.showplace.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Band(
    val id: Int? = null,
    val name: String,
    val genre: String?,
    val url: String?,
    val isLocal: Boolean
)
