package will.shiro.domain.util.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String): Date {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        Date()
    }
}

fun Date.format(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}