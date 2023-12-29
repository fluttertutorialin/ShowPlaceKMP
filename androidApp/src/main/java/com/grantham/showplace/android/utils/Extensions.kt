package com.grantham.showplace.android.utils

import java.util.Locale

fun String.toTitleCase(): String =
    this.lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }