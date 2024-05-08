package app.mistercooper.social.domain.common.mapper

import java.text.SimpleDateFormat
import java.util.Date

fun String.convertDateFromServer(): Date {
    val fechaString = "2024-05-08T20:38:51.000+02:00"
    val formato = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    return  formato.parse(fechaString)
}