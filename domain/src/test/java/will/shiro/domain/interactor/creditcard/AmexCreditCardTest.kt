package will.shiro.domain.interactor.creditcard

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import will.shiro.domain.entity.CreditCard

class AmexCreditCardTest {

    @Test
    fun `amex is valid starting with 34`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "3434 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Amex(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `amex is valid starting with 37`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "3737 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Amex(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `amex has invalid prefix`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "3343 5555 5555 5555"
        assertEquals(
            CreditCardInstitution.Amex(mockedCreditCard).isValid(),
            false
        )
    }

    @Test
    fun `amex has invalid length`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "3434"
        assertEquals(
            CreditCardInstitution.Amex(mockedCreditCard).isValid(),
            false
        )
    }
}