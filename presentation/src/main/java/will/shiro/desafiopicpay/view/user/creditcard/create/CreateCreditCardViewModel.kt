package will.shiro.desafiopicpay.view.user.creditcard.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.extensions.defaultPlaceholders
import will.shiro.desafiopicpay.util.extensions.defaultSched
import will.shiro.desafiopicpay.util.scheduler.SchedulerProvider
import will.shiro.domain.interactor.creditcard.SaveCreditCard
import will.shiro.domain.util.form.CreditCardFormFields
import javax.inject.Inject

class CreateCreditCardViewModel @Inject constructor(
    private val creditCardFormFields: CreditCardFormFields,
    private val saveCreditCard: SaveCreditCard,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val shouldEnableSave: LiveData<Boolean> get() = _shouldEnableSave

    private val _shouldEnableSave = MutableLiveData<Boolean>()

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
                // TODO tratamento de salvar
                val a = it
            }
    }
}