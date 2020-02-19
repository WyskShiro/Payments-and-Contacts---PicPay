package will.shiro.domain.util.extension

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class DateExtensionsTest {

    @Test
    fun `string to date success mm_yy`() {
        val converted = "11/22".toDate(MM_YY)
        val calendar = Calendar.getInstance().apply { time = converted }
        assertEquals(
            calendar.get(Calendar.MONTH),
            10
        )
        assertEquals(
            calendar.get(Calendar.YEAR),
            2022
        )
    }

    @Test
    fun `string to date success dd_mm_yyyy `() {
        val converted = "11/11/2011".toDate(DD_MM_YYYY)
        val calendar = Calendar.getInstance().apply { time = converted }
        assertEquals(
            calendar.get(Calendar.DAY_OF_MONTH),
            11
        )
        assertEquals(
            calendar.get(Calendar.MONTH),
            10
        )
        assertEquals(
            calendar.get(Calendar.YEAR),
            2011
        )
    }

    @Test
    fun `string to date failure has a default return`() {
        val converted = "11/22".toDate("")
        assertNotNull(converted)
    }

    @Test
    fun `date to strings success today`() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        assertEquals(
            "$hour:$minute",
            calendar.time.format(HH_MM)
        )
    }
}