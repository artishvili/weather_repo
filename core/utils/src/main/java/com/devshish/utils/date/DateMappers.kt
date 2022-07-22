package com.devshish.utils.date

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private val utcTimeZone = TimeZone.getTimeZone("UTC")

/**
 * Parse [String] into [Date] according to a provided [pattern]
 */
fun String.parseToDate(
    pattern: String,
    timeZone: TimeZone = utcTimeZone
): Date {
    val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
    formatter.timeZone = timeZone
    return formatter.parse(this) ?: error("Wrong date format!")
}

/**
 * Formats [Date] to [String] according to a provided [pattern]
 */
fun Date.formatDate(
    pattern: String,
    timeZone: TimeZone = TimeZone.getDefault()
): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    dateFormat.timeZone = timeZone
    return dateFormat.format(this)
}

/**
 * Date format pattern for current date - yyyy-MM-dd.
 *
 * Example - 2022-07-15
 */
const val CURRENT_DATE_PATTERN = "yyyy-MM-dd"

/**
 * Date format pattern for current date block - EEE, MMM dd.
 *
 * Example - Tue, July 14
 */
const val CURRENT_BLOCK_DATE_PATTERN = "EEE, MMM dd"

/**
 * Date format pattern for hourly weather - HH:mm.
 *
 * Example - 21:00
 */
const val TIME_DATE_PATTERN = "HH:mm"

/**
 * Date format pattern for hourly weather api response - HH:mm:ss.
 *
 * Example - 21:12:12
 */
const val RESPONSE_TIME_PATTERN = "HH:mm:ss"

/**
 * Date format pattern for daily weather - EEEE.
 *
 * Example - Tuesday
 */
const val DAY_PATTERN = "EEEE"
