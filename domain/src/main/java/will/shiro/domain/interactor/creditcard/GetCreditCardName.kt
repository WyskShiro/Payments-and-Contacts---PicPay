package will.shiro.domain.interactor.creditcard

import will.shiro.domain.entity.CreditCard
import will.shiro.domain.interactor.creditcard.CreditCardInstitution.*
import javax.inject.Inject

class GetCreditCardName @Inject constructor() {

    fun execute(creditCard: CreditCard): String {
        return when {
            Visa(creditCard).isValid() -> "Visa"
            Mastercard(creditCard).isValid() -> "Mastercard"
            Discover(creditCard).isValid() -> "Discover"
            Amex(creditCard).isValid() -> "Amex"
            else -> "Mastercard"
        }
    }
}