package will.shiro.domain.util.extension

fun String.onlyNumbers(): String {
    return replace(Regex(ONLY_NUMBERS_PATTERN), "")
}