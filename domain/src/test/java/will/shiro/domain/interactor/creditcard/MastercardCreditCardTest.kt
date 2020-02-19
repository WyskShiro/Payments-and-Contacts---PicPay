package will.shiro.domain.interactor.creditcard

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import will.shiro.domain.entity.CreditCard

class MastercardCreditCardTest {

    @Test
    fun `mastercard is valid starting with 51`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "5144 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Mastercard(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `mastercard is valid starting with 52`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "5244 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Mastercard(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `mastercard is valid starting with 53`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "5344 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Mastercard(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `mastercard is valid starting with 54`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "5444 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Mastercard(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `mastercard is valid starting with 55`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "5444 4444 4444 4444"
        assertEquals(
            CreditCardInstitution.Mastercard(mockedCreditCard).isValid(),
            true
        )
    }

    @Test
    fun `mastercard has invalid prefix`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "4444 5555 5555 5555"
        assertEquals(
            CreditCardInstitution.Mastercard(mockedCreditCard).isValid(),
            false
        )
    }

    @Test
    fun `mastercard has invalid length`() {
        val mockedCreditCard = mockk<CreditCard>()
        every { mockedCreditCard.number } returns "5151"
        assertEquals(
            CreditCardInstitution.Mastercard(mockedCreditCard).isValid(),
            false
        )
    }
}