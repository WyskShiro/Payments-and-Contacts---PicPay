package will.shiro.domain.interactor.creditcard

import will.shiro.domain.entity.CreditCard
import will.shiro.domain.util.extension.onlyNumbers

sealed class CreditCardInstitution(val creditCard: CreditCard) {
    open fun isValid(): Boolean {
        return creditCard.number.onlyNumbers().length == 16
    }

    class Visa(creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            return creditCard.number.startsWith("4") && super.isValid()
        }
    }

    class Mastercard(creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            val validPrefixes = arrayOf("51", "52", "53", "54", "55")
            return validPrefixes.contains(creditCard.number.substring(0 until 2)) && super.isValid()
        }
    }

    class Discover(creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            return with(creditCard.number) {
                (startsWith("6011") || startsWith("644") || startsWith("65")) &&
                        super.isValid()
            }
        }
    }

    class Amex(creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            return with(creditCard.number) {
                (startsWith("34") || startsWith("37")) && super.isValid()
            }
        }
    }
}