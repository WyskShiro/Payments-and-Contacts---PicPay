package will.shiro.desafiopicpay.view.user.creditcard.receipt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.extensions.defaultSched
import will.shiro.desafiopicpay.util.scheduler.SchedulerProvider
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.interactor.creditcard.GetCreditCard
import javax.inject.Inject

class ReceiptCreditCardViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getCreditCard: GetCreditCard
) : BaseViewModel() {

    init {
        getCreditCard()
    }

    val creditCard: LiveData<CreditCard> get() = _creditCard

    private val _creditCard by lazy { MutableLiveData<CreditCard>() }

    private fun getCreditCard() {
        getCreditCard.execute()
            .defaultSched(schedulerProvider)
            .subscribeBy({ setDialog(it, ::getCreditCard) }) {
                _creditCard.value = it
            }
    }
}