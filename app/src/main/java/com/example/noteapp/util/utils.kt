package com.example.noteapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(item: Long): String {
    val date = Date(item)
    val format = SimpleDateFormat("yyyy-mm-dd hh:mm:ss",
        Locale.getDefault())
    return format.format(date)
}