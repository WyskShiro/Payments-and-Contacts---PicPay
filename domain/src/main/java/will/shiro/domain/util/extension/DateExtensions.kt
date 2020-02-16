package will.shiro.domain.util.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String): Date {
    return SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
}

fun Date.format(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}