package will.shiro.domain.util.form

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import will.shiro.domain.util.form.CreditCardFormFields.Companion.CVV
import will.shiro.domain.util.form.CreditCardFormFields.Companion.EXPIRATION_DATE
import will.shiro.domain.util.form.CreditCardFormFields.Companion.NUMBER
import will.shiro.domain.util.form.CreditCardFormFields.Companion.OWNER_NAME
import will.shiro.domain.util.form.validation.EmptyStringValidation
import will.shiro.domain.util.form.validation.SpecificSizeValidation

class CreditCardFormFieldsTest {

    @MockK
    private lateinit var mockedNumber: SpecificSizeValidation
    @MockK
    private lateinit var mockedOwnerName: EmptyStringValidation
    @MockK
    private lateinit var mockedExpirationDate: SpecificSizeValidation
    @MockK
    private lateinit var mockedCvv: SpecificSizeValidation
    @SpyK
    var creditCardFormFields: CreditCardFormFields = CreditCardFormFields()

    init {
        MockKAnnotations.init(this)
    }

    @Before
    fun setUp() {
        every { mockedNumber.isValid() } returns true
        every { mockedOwnerName.isValid() } returns true
        every { mockedExpirationDate.isValid() } returns true
        every { mockedCvv.isValid() } returns true
    }

    @Test
    fun `has valid fields`() {
        creditCardFormFields.fields = hashMapOf(
            NUMBER to mockedNumber,
            OWNER_NAME to mockedOwnerName,
            EXPIRATION_DATE to mockedExpirationDate,
            CVV to mockedCvv
        )
        assertEquals(
            true,
            creditCardFormFields.isValid()
        )
    }

    @Test
    fun `has at least one invalid fields`() {
        every { mockedCvv.isValid() } returns false
        creditCardFormFields.fields = hashMapOf(
            NUMBER to mockedNumber,
            OWNER_NAME to mockedOwnerName,
            EXPIRATION_DATE to mockedExpirationDate,
            CVV to mockedCvv
        )
        assertEquals(
            false,
            creditCardFormFields.isValid()
        )
    }
}