package will.shiro.desafiopicpay.util.mask

import android.text.InputFilter
import android.text.InputType
import android.widget.EditText

object CreditCardMask {
    fun apply(editText: EditText) {
        editText.filters = arrayOf(InputFilter.LengthFilter(19))
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.addTextChangedListener(CommonPatternMask(editText, " ", 4))
    }
}