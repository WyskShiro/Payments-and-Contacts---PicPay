package will.shiro.domain.util.extension

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.fail
import java.lang.Exception

class StringExtensionsTest {

    @Test
    fun `returns only numbers from string`() {
        val stringWithNumbersAndLetters = "qwert1234asdf5678zxc90"
        assertEquals(
            "1234567890",
            stringWithNumbersAndLetters.onlyNumbers()
        )
    }

    @Test
    fun `string with no numbers is empty`() {
        val stringWithNumbersAndLetters = "qwert!@#$%(*&"
        assertEquals(
            "",
            stringWithNumbersAndLetters.onlyNumbers()
        )
    }

    @Test
    fun `format empty string`() {
        val emptyString = ""
        assertEquals(
            DEFAULT_NO_MONEY,
            emptyString.formatAsMoney()
        )
    }

    @Test
    fun `format size one sized string`() {
        val one = "1"
        assertEquals(
            "0,01",
            one.formatAsMoney()
        )
    }

    @Test
    fun `format size two sized string`() {
        val two = "22"
        assertEquals(
            "0,22",
            two.formatAsMoney()
        )
    }

    @Test
    fun `format size three sized string`() {
        val three = "333"
        assertEquals(
            "3,33",
            three.formatAsMoney()
        )
    }

    @Test
    fun `converted with comma and dots`() {
        val bigNumber = "123456789"
        assertEquals(
            "1.234.567,89",
            bigNumber.formatAsMoney()
        )
    }

    @Test
    fun `string with characters that are not numbers`() {
        val mixedCharacters = "a1s2d3f4g5h6"
        assertEquals(
            "1.234,56",
            mixedCharacters.formatAsMoney()
        )
    }

    @Test
    fun `empty string`() {
        val emptyString = ""
        assertEquals(
            DECIMAL_SEPARATOR,
            emptyString.insertSymbol(0)
        )
    }

    @Test
    fun `string with at least one character`() {
        val notEmptyString = "a"
        assertEquals(
            "${DECIMAL_SEPARATOR}a",
            notEmptyString.insertSymbol(0)
        )
    }

    @Test
    fun `invalid position for insert symbol`() {
        val oneChar = "q"
        assertEquals(
            oneChar,
            oneChar.insertSymbol(oneChar.length + 1)
        )
    }
}