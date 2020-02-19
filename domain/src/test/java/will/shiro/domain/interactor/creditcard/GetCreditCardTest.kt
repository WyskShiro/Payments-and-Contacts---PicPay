package will.shiro.domain.interactor.creditcard

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test
import will.shiro.domain.boundary.CreditCardRepository
import will.shiro.domain.entity.CreditCard

class GetCreditCardTest {

    @Test
    fun `has received a name`() {
        val mockedCreditCard = spyk<CreditCard>()
        val mockedCreditCardRepository = mockk<CreditCardRepository>()
        val mockedGetCreditCardName = mockk<GetCreditCardName>()
        val getCreditCard = GetCreditCard(mockedCreditCardRepository, mockedGetCreditCardName)
        every { mockedCreditCardRepository.getOne() } returns Single.just(mockedCreditCard)
        every { mockedGetCreditCardName.execute(mockedCreditCard) } returns "Name"
        assertEquals(
            getCreditCard.execute().blockingGet().cardName,
            "Name"
        )
    }
}