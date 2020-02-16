package will.shiro.desafiopicpay.util.mask

import android.widget.EditText
import will.shiro.desafiopicpay.util.watcher.SimpleTextWatcher

class CommonPatternMask constructor(
    private val editText: EditText,
    private val pattern: String,
    private val patternSize: Int
) : SimpleTextWatcher() {
    private var isUpdating: Boolean = false
    private var oldText = ""

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (isUpdating) {
            isUpdating = false
        } else {
            val text = s.toString().replace(pattern, "")
            if (text.length > oldText.length) {
                val maskedText = text.chunked(patternSize).joinToString(separator = pattern)
                isUpdating = true
                editText.setText(maskedText)
                if (maskedText.isNotEmpty()) {
                    editText.setSelection(maskedText.length)
                }
            }
            oldText = text
        }
    }
}