package com.grantham.showplace

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

actual object DateFormatter {
    actual fun formatLocalDate(localDate: LocalDate): String {
        return localDate.toJavaLocalDate().format(DateTimeFormatter.ofPattern("E d, MMM yyyy"))
            ?: "tdb"
    }
}