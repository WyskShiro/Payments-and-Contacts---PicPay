package will.shiro.desafiopicpay.util.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import will.shiro.desafiopicpay.R

class SearchEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : AppCompatEditText(context, attributeSet) {

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        text?.let {

            gravity = if (it.isNotEmpty()) {
                Gravity.START or Gravity.CENTER_VERTICAL
            } else {
                Gravity.CENTER
            }
        }
    }
}