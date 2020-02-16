package will.shiro.desafiopicpay.util.mask

import android.text.InputFilter
import android.text.InputType
import android.widget.EditText
import will.shiro.domain.util.form.CreditCardFormFields

object CreditCardEditConfigurations {
    fun apply(editText: EditText) {
        editText.apply {
            filters = arrayOf(InputFilter.LengthFilter(CreditCardFormFields.NUMBER_SIZE))
            inputType = InputType.TYPE_CLASS_NUMBER
            addTextChangedListener(CommonPatternMask(editText, " ", 4))
        }
    }
}