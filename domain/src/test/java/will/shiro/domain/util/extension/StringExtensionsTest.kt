package will.shiro.domain.util.extension

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class StringExtensionsTest {

    @Test
    fun `returns only numbers from string`() {
        val stringWithNumbersAndLetters = "qwert1234asdf5678zxc90"
        assertEquals(
            stringWithNumbersAndLetters.onlyNumbers(),
            "1234567890"
        )
    }

    @Test
    fun `string with no numbers is empty`() {
        val stringWithNumbersAndLetters = "qwert!@#$%(*&"
        assertEquals(
            stringWithNumbersAndLetters.onlyNumbers(),
            ""
        )
    }
}