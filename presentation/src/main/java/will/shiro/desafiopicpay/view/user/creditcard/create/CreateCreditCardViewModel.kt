package will.shiro.desafiopicpay.view.user.creditcard.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.domain.util.form.CreditCardFormFields
import javax.inject.Inject

class CreateCreditCardViewModel @Inject constructor(
    private val creditCardFormFields: CreditCardFormFields
) : BaseViewModel() {

    val shouldEnableSave: LiveData<Boolean> get() = _shouldEnableSave

    private val _shouldEnableSave = MutableLiveData<Boolean>()

    fun onInputTextChanged(text: String, identifier: String) {
        creditCardFormFields.fields[identifier]?.field = text
        _shouldEnableSave.value = creditCardFormFields.isValid()
    }
}