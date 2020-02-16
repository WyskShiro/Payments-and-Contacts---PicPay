package will.shiro.desafiopicpay.view.user.creditcard.payment

import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.view.user.creditcard.payment.PaymentCreditCardProvider.NAMED_CREDIT_CARD
import will.shiro.desafiopicpay.view.user.creditcard.payment.PaymentCreditCardProvider.NAMED_USER
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.entity.User
import will.shiro.domain.util.extension.ONLY_NUMBERS_PATTERN
import will.shiro.domain.util.extension.onlyNumbers
import javax.inject.Inject
import javax.inject.Named

class PaymentCreditCardViewModel @Inject constructor(
    @Named(NAMED_USER) private val user: User,
    @Named(NAMED_CREDIT_CARD) private val creditCard: CreditCard
) : BaseViewModel() {

    var value = 0f

    fun onMoneyValueChanged(newValue: String) {
        newValue.onlyNumbers().toFloatOrNull()?.let {
            value = it
        } ?: kotlin.run {
            value = 0f
        }
    }
}