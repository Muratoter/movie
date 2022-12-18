package com.moter.tmdb.ui.extensions

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by muratoter on 11,December,2022
 */

fun Double.decimalFormat(): String {
    return DecimalFormat("0.#").format(this).replace(',', '.')
}

fun String.formatDate(): String {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(this)

        val expectedFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        expectedFormat.format(date)
    } catch (e: Exception) {
        this
    }
}

fun String.getReleaseYear(): String {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(this)

        val expectedFormat = SimpleDateFormat("yyyy", Locale.getDefault())

        expectedFormat.format(date)
    } catch (e: Exception) {
        this
    }
}