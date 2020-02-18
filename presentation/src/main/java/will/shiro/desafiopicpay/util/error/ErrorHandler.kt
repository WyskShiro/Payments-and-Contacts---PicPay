package will.shiro.desafiopicpay.util.error

import will.shiro.domain.util.Strings
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val strings: Strings
) {

    fun getDialogData(
        throwable: Throwable,
        retryAction: (() -> Unit)?,
        onDismiss: (() -> Unit)? = null
    ): DialogData {
        val data = getPlaceholder(throwable, retryAction)
        return if (data.message == null) {
            DialogData.error(strings, getUnknownErrorMessage(), onDismiss = onDismiss)
        } else {
            DialogData.error(strings, data.message, data.buttonText, data.buttonAction)
        }
    }

    fun getPlaceholder(throwable: Throwable, retryAction: (() -> Unit)?): Placeholder {
        return Placeholder.Action(
            getUnknownErrorMessage(),
            strings.globalTryAgain,
            retryAction ?: {}
        )
    }

    private fun getUnknownErrorMessage(): String {
        return strings.errorUnknown
    }
}
