package will.shiro.desafiopicpay.util.extensions

import android.text.Editable
import android.widget.EditText
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