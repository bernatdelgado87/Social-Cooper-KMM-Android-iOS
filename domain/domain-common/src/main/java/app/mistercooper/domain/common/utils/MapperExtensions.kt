package app.mistercooper.domain.common.utils

import java.text.SimpleDateFormat
import java.util.Date

fun String.convertDateFromServer(): Date {
    val formato = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    return  formato.parse(this)
}