package will.shiro.desafiopicpay.view.user.creditcard.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.extensions.defaultPlaceholders
import will.shiro.desafiopicpay.util.extensions.defaultSched
import will.shiro.desafiopicpay.util.scheduler.SchedulerProvider
import will.shiro.desafiopicpay.view.user.creditcard.payment.PaymentCreditCardProvider.NAMED_CREDIT_CARD
import will.shiro.desafiopicpay.view.user.creditcard.payment.PaymentCreditCardProvider.NAMED_USER
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.entity.TransactionRequest
import will.shiro.domain.entity.User
import will.shiro.domain.interactor.user.CreateTransaction
import will.shiro.domain.util.extension.format
import will.shiro.domain.util.extension.onlyNumbers
import javax.inject.Inject
import javax.inject.Named

class PaymentCreditCardViewModel @Inject constructor(
    @Named(NAMED_USER) private val user: User,
    @Named(NAMED_CREDIT_CARD) private val creditCard: CreditCard,
    private val createTransaction: CreateTransaction,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val shouldEnablePay: LiveData<Boolean> get() = _shouldEnablePay
    var value = 0f

    private val _shouldEnablePay by lazy { MutableLiveData<Boolean>() }

    fun onMoneyValueChanged(newValue: String) {
        newValue.onlyNumbers().toFloatOrNull()?.let {
            value = it
            _shouldEnablePay.value = it > 0
        } ?: run {
            value = 0f
            _shouldEnablePay.value = false
        }
    }

    fun onPay() {
        createTransaction.execute(
            TransactionRequest(
                cardNumber = creditCard.number,
                cvv = creditCard.cvv,
                value = value,
                expiryDate = creditCard.expirationDate.format("MM/yy"),
                destinationUserId = user.id
            )
        )
            .defaultSched(schedulerProvider)
            .defaultPlaceholders(::setPlaceholder)
            .subscribeBy({ setDialog(it, ::onPay) }) {
                val a = it
                // TODO tratar erro
                // TODO finish + mostrar recibo
            }
    }
}