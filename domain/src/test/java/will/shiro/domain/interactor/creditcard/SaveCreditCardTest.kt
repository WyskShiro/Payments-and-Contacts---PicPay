package will.shiro.domain.interactor.creditcard

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test
import will.shiro.domain.boundary.CreditCardRepository
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.util.form.CreditCardFormFields

class SaveCreditCardTest {

    @Test
    fun `has called to save credit card`() {
        val mockedCreditCardRepository = spyk<CreditCardRepository>()
        val mockedCreditCardFormFields = mockk<CreditCardFormFields>()
        val mockedCreditCard = mockk<CreditCard>()
        val saveCreditCard = SaveCreditCard(mockedCreditCardRepository)

        every { mockedCreditCardFormFields.buildCreditCard() } returns mockedCreditCard

        saveCreditCard.execute(mockedCreditCardFormFields)

        verify(exactly = 1) {
            mockedCreditCardRepository.addOrUpdate(
                mockedCreditCard
            )
        }
    }
}