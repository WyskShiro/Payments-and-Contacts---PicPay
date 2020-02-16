package will.shiro.domain.util.form

import will.shiro.domain.entity.CreditCard
import will.shiro.domain.util.extension.toDate
import will.shiro.domain.util.form.validation.EmptyStringValidation
import will.shiro.domain.util.form.validation.SpecificSizeValidation
import javax.inject.Inject

class CreditCardFormFields @Inject constructor() {

    var fields = hashMapOf(
        NUMBER to SpecificSizeValidation(minFieldSize = NUMBER_SIZE),
        OWNER_NAME to EmptyStringValidation(),
        EXPIRATION_DATE to SpecificSizeValidation(minFieldSize = EXPIRATION_DATE_SIZE),
        CVV to SpecificSizeValidation(minFieldSize = CVV_SIZE)
    )

    fun isValid(): Boolean {
        var isValid = true
        fields.map {
            if (!it.value.isValid()) {
                isValid = false
            }
        }
        return isValid
    }

    fun buildCreditCard(): CreditCard {
        return CreditCard(
            number = fields[NUMBER]?.field!!,
            ownerName = fields[OWNER_NAME]?.field!!,
            expirationDate = fields[EXPIRATION_DATE]?.field?.toDate("MM/yy")!!,
            cvv = fields[CVV]?.field?.toInt()!!
        )
    }

    companion object {
        const val NUMBER = "NUMBER"
        const val NUMBER_SIZE = 19
        const val OWNER_NAME = "OWNER_NAME"
        const val EXPIRATION_DATE = "EXPIRATION_DATE"
        const val EXPIRATION_DATE_SIZE = 5
        const val CVV = "CVV"
        const val CVV_SIZE = 3
    }
}