package will.shiro.domain.interactor.creditcard

import will.shiro.domain.entity.CreditCard

sealed class CreditCardInstitution(creditCard: CreditCard) {
    abstract fun isValid(): Boolean

    class Visa(val creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            return creditCard.number.startsWith("4")
        }
    }

    class Mastercard(val creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            val validPrefixes = arrayOf("51", "52", "53", "54", "55")
            return validPrefixes.contains(creditCard.number.substring(0 until 2))
        }
    }

    class Discover(val creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            return with(creditCard.number) {
                startsWith("6011") || startsWith("644") || startsWith("65")
            }
        }
    }

    class Amex(val creditCard: CreditCard) : CreditCardInstitution(creditCard) {
        override fun isValid(): Boolean {
            return with(creditCard.number) {
                startsWith("34") || startsWith("37")
            }
        }
    }
}