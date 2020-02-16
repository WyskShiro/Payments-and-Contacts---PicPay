package will.shiro.desafiopicpay.util.mask

import android.text.InputFilter
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import will.shiro.domain.util.form.CreditCardFormFields

object CVVEditConfigurations {
    fun apply(editText: EditText) {
        editText.apply {
            filters = arrayOf(InputFilter.LengthFilter(CreditCardFormFields.CVV_SIZE))
            inputType = InputType.TYPE_CLASS_NUMBER
            imeOptions = EditorInfo.IME_ACTION_DONE
        }
    }
}