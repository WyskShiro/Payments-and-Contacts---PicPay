package will.shiro.desafiopicpay.util.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import setClick
import will.shiro.desafiopicpay.databinding.CustomMoneyInputViewBinding
import will.shiro.desafiopicpay.util.watcher.SimpleTextWatcher

class MoneyInputLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    val editText: EditText by lazy {
        binding.editText
    }
    val textView: TextView by lazy {
        binding.textView
    }
    private val binding: CustomMoneyInputViewBinding = CustomMoneyInputViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        editText.setClick {
            editText.setSelection(editText.text.length)
        }
        editText.addTextChangedListener(object : SimpleTextWatcher() {
            var oldText = editText.text.toString()
            var isUpdating = false

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s.toString()
                if (isUpdating) {
                    isUpdating = false
                } else {
                    var newTextWithoutSymbols = newText.replace(Regex("[^0-9]"), "")
                    if (newText.length < 4) {
                        newTextWithoutSymbols = "0" + newTextWithoutSymbols.substring(0 until 2)
                        newTextWithoutSymbols = newTextWithoutSymbols.substring(0 until 1) +
                                "," + newTextWithoutSymbols.substring(1 until newTextWithoutSymbols.length)
                        isUpdating = true
                        editText.setText(newTextWithoutSymbols)
                        oldText = newTextWithoutSymbols
                        editText.setSelection(4)
                    } else {
                        if (newTextWithoutSymbols.startsWith("0")) {
                            newTextWithoutSymbols = newTextWithoutSymbols.removeRange(0, 1)
                        }

                        var i = newTextWithoutSymbols.length - 2
                        newTextWithoutSymbols = newTextWithoutSymbols.substring(0 until i) +
                                "," + newTextWithoutSymbols.substring(i until newTextWithoutSymbols.length)
                        i -= 3
                        while (i > 0) {
                            newTextWithoutSymbols = newTextWithoutSymbols.substring(0 until i) +
                                    "." + newTextWithoutSymbols.substring(i until newTextWithoutSymbols.length)
                            i -= 3
                        }
                        isUpdating = true
                        editText.setText(newTextWithoutSymbols)
                        editText.setSelection(newTextWithoutSymbols.length)
                        oldText = newTextWithoutSymbols
                    }
                }
            }

            private fun insertComma(text: String, position: Int): String {
                return text.substring(0 until position) +
                        "," + text.substring(position until text.length)
            }
        })
    }
}