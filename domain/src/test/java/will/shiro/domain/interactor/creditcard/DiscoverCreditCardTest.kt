package will.shiro.domain.interactor.creditcard

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import will.shiro.domain.entity.CreditCard

class DiscoverCreditCardTest {

    @Test
    fun `discover is valid starting with 6011`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "6011 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Discover(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `discover is valid starting with 644`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "6444 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Discover(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `discover is valid starting with 65`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "6565 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Discover(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `discover has invalid prefix`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "6165 5555 5555 5555"
        assertEquals(
            CreditCardInstitution.Discover(mockedCreditCard).isValid(),
            false
        )
    }

    @Test
    fun `discover has invalid length 6565`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "6565"
        assertEquals(
            CreditCardInstitution.Discover(mockedCreditCard).isValid(),
            false
        )
    }

    @Test
    fun `discover has invalid length 6011 6011`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "6011 6011"
        assertEquals(
            CreditCardInstitution.Discover(mockedCreditCard).isValid(),
            false
        )
    }
}