package com.grantham.showplace

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import platform.Foundation.NSDate
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual object DateFormatter {
    actual fun formatLocalDate(localDate: LocalDate): String {

        val date = localDate.toNsDate()
            ?: throw IllegalStateException("Failed to convert LocalDateTime $LocalDateTime to NSDate")

        val formatter = NSDateFormatter().apply {
            dateFormat = "E d, MMM yyyy"
            locale = NSLocale.currentLocale
        }

        return formatter.stringFromDate(date)
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun LocalDate.toNsDate(): NSDate? {
        val calendar = NSCalendar.currentCalendar
        val components = NSDateComponents()
        components.year = this.year.convert()
        components.month = this.monthNumber.convert()
        components.day = this.dayOfMonth.convert()
        return calendar.dateFromComponents(components)
    }
}