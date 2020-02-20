package will.shiro.desafiopicpay.util.mask

import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.util.watcher.SimpleTextWatcher
import will.shiro.domain.util.extension.DEFAULT_NO_MONEY
import will.shiro.domain.util.extension.formatAsMoney
import will.shiro.domain.util.extension.onlyNumbers

class MoneyMask(
    private val editText: EditText,
    private val textView: TextView
) : SimpleTextWatcher() {
    var oldText = editText.text.toString()
    var isUpdating = false

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (isUpdating) {
            isUpdating = false
        } else {
            val newText = s.toString().formatAsMoney()
            isUpdating = true
            editText.setText(newText)
            oldText = newText
            try {
                editText.setSelection(newText.length)
            } catch (e: IndexOutOfBoundsException) {
                editText.setSelection(editText.length())
            }
            changeInputColor(newText)
        }
    }

    private fun changeInputColor(text: String) {
        val resourceColor = if (text == DEFAULT_NO_MONEY) {
            R.color.colorWhite40
        } else {
            R.color.colorGreen
        }
        editText.setTextColor(ContextCompat.getColor(editText.context, resourceColor))
        textView.setTextColor(ContextCompat.getColor(textView.context, resourceColor))
    }
}