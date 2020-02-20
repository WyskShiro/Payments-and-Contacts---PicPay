package will.shiro.domain.interactor.creditcard

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import will.shiro.domain.entity.CreditCard

class VisaCreditCardTest {

    @Test
    fun `visa is valid`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "4444 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Visa(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `visa has invalid prefix`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "5555 5555 5555 5555"
        assertEquals(
            CreditCardInstitution.Visa(mockedCreditCard).isValid(),
            false
        )
    }

    @Test
    fun `visa has invalid length`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "4444"
        assertEquals(
            CreditCardInstitution.Visa(mockedCreditCard).isValid(),
            false
        )
    }
}