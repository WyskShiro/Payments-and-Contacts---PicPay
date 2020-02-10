package will.shiro.desafiopicpay.util.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import will.shiro.desafiopicpay.util.error.DialogData
import will.shiro.desafiopicpay.util.error.ErrorHandler
import will.shiro.desafiopicpay.util.error.Placeholder
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var errorHandler: ErrorHandler

    val disposables = CompositeDisposable()

    val toast: LiveData<Event<String>> get() = toastLiveData
    val placeholder: LiveData<Placeholder> get() = placeholderLiveData
    val dialog: LiveData<Event<DialogData>> get() = dialogLiveData

    private val toastLiveData = MutableLiveData<Event<String>>()
    private val placeholderLiveData = MutableLiveData<Placeholder>()
    private val dialogLiveData = MutableLiveData<Event<DialogData>>()

    fun setPlaceholder(placeholder: Placeholder) {
        placeholderLiveData.postValue(placeholder)
    }

    fun setDialog(dialogData: DialogData) {
        dialogLiveData.postValue(Event(dialogData))
    }

    fun setDialog(
        throwable: Throwable,
        retryAction: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        setDialog(errorHandler.getDialogData(throwable, retryAction, onDismiss))
    }

    fun setDialog(throwable: Throwable) {
        setDialog(errorHandler.getDialogData(throwable, null, null))
    }

    fun showToast(message: String) {
        toastLiveData.value = Event(message)
    }

    @CallSuper
    override fun onCleared() {
        disposables.clear()
    }
}