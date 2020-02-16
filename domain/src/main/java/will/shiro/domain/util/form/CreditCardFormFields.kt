package will.shiro.domain.util.form

import will.shiro.domain.entity.CreditCard
import will.shiro.domain.util.extension.toDate
import will.shiro.domain.util.form.validation.EmptyValidation
import javax.inject.Inject

class CreditCardFormFields @Inject constructor() {

    var fields = hashMapOf(
        NUMBER to EmptyValidation(),
        OWNER_NAME to EmptyValidation(),
        EXPIRATION_DATE to EmptyValidation(),
        CVV to EmptyValidation()
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
        const val OWNER_NAME = "OWNER_NAME"
        const val EXPIRATION_DATE = "EXPIRATION_DATE"
        const val CVV = "CVV"
    }
}