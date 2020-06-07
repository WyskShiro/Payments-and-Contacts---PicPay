package will.shiro.desafiopicpay.util.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import setClick
import will.shiro.desafiopicpay.databinding.CustomMoneyInputViewBinding
import will.shiro.desafiopicpay.util.mask.MoneyMask

class MoneyInputLayout2 @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

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
        editText.addTextChangedListener(MoneyMask(editText, textView))
    }
}