package will.shiro.desafiopicpay.util.resources

import android.content.Context
import androidx.annotation.StringRes
import will.shiro.desafiopicpay.R
import will.shiro.domain.util.Strings
import javax.inject.Inject

class AndroidStrings @Inject constructor(context: Context) : Strings {

    private val context = context.applicationContext

    override val errorTitle: String get() = res(R.string.error_title)
    override val errorUnknown: String get() = res(R.string.error_unknown)

    override val globalYes: String get() = res(R.string.global_yes)
    override val globalNo: String get() = res(R.string.global_no)
    override val globalWait: String get() = res(R.string.global_wait)
    override val globalTryAgain: String get() = res(R.string.global_try_again)
    override val globalCancel: String get() = res(R.string.global_cancel)
    override val globalOk: String get() = res(R.string.global_ok)
    override val globalSuccess: String get() = res(R.string.global_success)

    override val paymentCreditCardTransactionRejected: String get() = res(R.string.payment_credit_card_transaction_rejected)

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
    private fun resWithParams(@StringRes stringId: Int, vararg params: String): String {
        return context.getString(stringId, *params)
    }
}