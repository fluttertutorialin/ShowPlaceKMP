package com.grantham.showplace

import co.touchlab.kermit.Logger
import com.grantham.showplace.data.models.Show
import com.grantham.showplace.data.models.Venue
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf

actual val showPreview = Show(
    id = "5",
    lineup = "Metal, Fun Name, Great Band",
    venue = Venue(
        name = "Fun Venue",
        ageLimit = "none",
        accessibility = "blah",
        url = "",
        capacity = ""
    ),
    time = "9pm",
    date = Clock.System.todayIn(TimeZone.currentSystemDefault()).toString(),
    price = "Free"
)

fun Koin.loggerWithTag(tag: String) = get<Logger>(qualifier = null) { parametersOf(tag) }