package will.shiro.desafiopicpay.view.user.creditcard.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.extensions.defaultPlaceholders
import will.shiro.desafiopicpay.util.extensions.defaultSched
import will.shiro.desafiopicpay.util.scheduler.SchedulerProvider
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.interactor.creditcard.SaveCreditCard
import will.shiro.domain.util.form.CreditCardFormFields
import javax.inject.Inject
import javax.inject.Named

class CreateCreditCardViewModel @Inject constructor(
    private val creditCardFormFields: CreditCardFormFields,
    private val saveCreditCard: SaveCreditCard,
    private val schedulerProvider: SchedulerProvider,
    @Named(CreateCreditCardProvider.NAMED_CREDIT_CARD) creditCard: CreditCard?
) : BaseViewModel() {

    val shouldEnableSave: LiveData<Boolean> get() = _shouldEnableSave
    val goToPaymentCreditCard: LiveData<CreditCard> get() = _goToPaymentCreditCard
    val fillWithCreditCard: LiveData<CreditCard> get() = _fillWithCreditCard

    private val _shouldEnableSave by lazy { MutableLiveData<Boolean>() }
    private val _goToPaymentCreditCard by lazy { MutableLiveData<CreditCard>() }
    private val _fillWithCreditCard by lazy { MutableLiveData<CreditCard>() }

    init {
        creditCard?.let {
            _fillWithCreditCard.value = it
        }
    }

    fun onInputTextChanged(text: String, identifier: String) {
        creditCardFormFields.fields[identifier]?.field = text
        _shouldEnableSave.value = creditCardFormFields.isValid()
    }

    fun saveCreditCard() {
        saveCreditCard.execute(creditCardFormFields)
            .defaultSched(schedulerProvider)
            .defaultPlaceholders(::setPlaceholder)
            .subscribeBy({
                setDialog(it, ::saveCreditCard)
            }) {
                _goToPaymentCreditCard.value = it
            }
    }
}