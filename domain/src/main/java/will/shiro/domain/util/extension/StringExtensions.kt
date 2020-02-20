package will.shiro.domain.util.extension

fun String.onlyNumbers(): String {
    return replace(Regex(ONLY_NUMBERS_PATTERN), "")
}

fun String.formatAsMoney(): String {
    var newText = onlyNumbers()
    when {
        newText.isEmpty() -> {
            newText = DEFAULT_NO_MONEY
        }
        newText.length == 1 -> {
            newText = "0,0$newText"
        }
        newText.length == 2 -> {
            newText = "0,$newText"
        }
        newText.length == 3 -> {
            newText = newText.insertSymbol(1)
        }
        else -> {
            if (newText.startsWith("0")) {
                newText = newText.removeRange(0, 1)
            }
            var symbolPosition = newText.length - DECIMAL_SEPARATOR_SIZE
            newText = newText.insertSymbol(symbolPosition)
            symbolPosition -= GROUP_SEPARATOR_SIZE
            while (symbolPosition > 0) {
                newText = newText.insertSymbol(symbolPosition, GROUP_SEPARATOR)
                symbolPosition -= GROUP_SEPARATOR_SIZE
            }
        }
    }
    return newText
}

fun String.insertSymbol(
    position: Int,
    symbol: String = DECIMAL_SEPARATOR
): String {
    return try {
        substring(0 until position) +
                symbol + substring(position until length)
    } catch (e: Exception) {
        this
    }
}