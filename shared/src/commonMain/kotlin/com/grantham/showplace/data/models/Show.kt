package com.grantham.showplace.data.models

import com.grantham.showplace.DateFormatter
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class ShowDTO(
    val id: Int,
    val date: LocalDate,
    val time: String?,
    val price: String?,
    val venue: Venue,
    val lineup: List<Band> = listOf(),
)

data class Show(
    val id: String,
    val date: String,
    val time: String? = null,
    val price: String,
    val venue: Venue,
    val lineup: String
)

fun ShowDTO.mapToShow(): Show {
    return Show(
        id = id.toString(),
        date = DateFormatter.formatLocalDate(date),
        time = time,
        price = price ?: "TBD",
        venue = venue,
        lineup = lineup.map { it.name }.joinToString(", ")
    )
}