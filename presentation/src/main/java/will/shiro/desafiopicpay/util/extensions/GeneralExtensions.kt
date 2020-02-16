package will.shiro.desafiopicpay.util.extensions

import android.text.Editable
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import will.shiro.desafiopicpay.util.watcher.SimpleTextWatcher

// EditText
fun EditText.createTextWatcher(afterTextChanged: (String?) -> Unit): SimpleTextWatcher {
    val textWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            afterTextChanged(s.toString())
        }
    }
    addTextChangedListener(textWatcher)
    return textWatcher
}

fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

fun NavController.navigateSafe(directions: NavDirections) {
    val resId = directions.actionId
    val action = currentDestination?.getAction(resId)
    if (action != null) navigate(directions)
}
