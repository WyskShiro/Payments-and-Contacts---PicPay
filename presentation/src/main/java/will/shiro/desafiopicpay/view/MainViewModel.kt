package will.shiro.desafiopicpay.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.base.Event
import will.shiro.desafiopicpay.util.error.Placeholder
import will.shiro.domain.entity.Transaction
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    val paymentSuccess: LiveData<Event<Transaction>> get() = _paymentSuccess

    private val _paymentSuccess by lazy { MutableLiveData<Event<Transaction>>() }

    fun onPaymentSuccess(transaction: Transaction?) {
        transaction?.let {
            _paymentSuccess.postValue(Event(it))
        }
    }

    fun onPlaceholder(placeholder: Placeholder?) {
        placeholder?.run(::setPlaceholder)
    }
}