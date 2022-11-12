package uz.qmgroup.debtbook

import android.icu.text.NumberFormat
import java.text.ParseException

fun NumberFormat.parseOrNull(string: String): Number? {
    return try {
        parse(string)
    } catch (e: ParseException) {
        null
    }
}