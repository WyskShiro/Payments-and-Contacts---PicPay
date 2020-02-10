package will.shiro.desafiopicpay.util.error

import will.shiro.domain.util.logger.Logger
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val logger: Logger
) {

    fun getDialogData(
        throwable: Throwable,
        retryAction: (() -> Unit)?,
        onDismiss: (() -> Unit)? = null
    ): DialogData {
        val data = getPlaceholder(throwable, retryAction)
        return if (data.message == null) {
            DialogData.error(getUnknownErrorMessage(), onDismiss = onDismiss)
        } else {
            DialogData.error(data.message, data.buttonText, data.buttonAction)
        }
    }

    fun getPlaceholder(throwable: Throwable, retryAction: (() -> Unit)?): Placeholder {
        logger.e(throwable)
        return Placeholder.Action(
            getUnknownErrorMessage(),
            "Try Again",
            retryAction ?: {}
        )
    }

    private fun getUnknownErrorMessage(): String {
        return "Oops. Something occurred. Try again later."
    }
}
