package com.grantham.showplace

import kotlinx.datetime.LocalDate

expect object DateFormatter {
    fun formatLocalDate(localDate: LocalDate): String
}