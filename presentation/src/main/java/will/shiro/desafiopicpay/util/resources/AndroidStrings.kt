package will.shiro.desafiopicpay.util.resources

import android.content.Context
import androidx.annotation.StringRes
import will.shiro.desafiopicpay.R
import javax.inject.Inject

class AndroidStrings @Inject constructor(context: Context) {

    private val context = context.applicationContext

    val errorTitle: String get() = res(R.string.error_title)
    val errorUnknown: String get() = res(R.string.error_unknown)

    val globalYes: String get() = res(R.string.global_yes)
    val globalNo: String get() = res(R.string.global_no)
    val globalWait: String get() = res(R.string.global_wait)
    val globalTryAgain: String get() = res(R.string.global_try_again)
    val globalCancel: String get() = res(R.string.global_cancel)
    val globalOk: String get() = res(R.string.global_ok)
    val globalSuccess: String get() = res(R.string.global_success)

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
    private fun resWithParams(@StringRes stringId: Int, vararg params: String): String {
        return context.getString(stringId, *params)
    }
}