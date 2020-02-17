package will.shiro.desafiopicpay.util.extensions

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
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

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(window.currentFocus?.windowToken, 0)
}

fun Fragment.forceToShowKeyboard() {
    val inputMethodManager =
        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_IMPLICIT,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

fun Fragment.openKeyboard(editText: EditText) {
    activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    forceToShowKeyboard()
    editText.setSelection(editText.text.length)
}

fun Float.convertCentToBasicUnit(): Float {
    return this / 100
}
