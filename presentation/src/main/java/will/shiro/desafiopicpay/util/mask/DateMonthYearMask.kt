package will.shiro.desafiopicpay.util.mask

import android.text.InputFilter
import android.text.InputType
import android.widget.EditText

object DateMonthYearMask {
    fun apply(editText: EditText) {
        editText.filters = arrayOf(InputFilter.LengthFilter(5))
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.addTextChangedListener(CommonPatternMask(editText, "/", 2))
    }
}