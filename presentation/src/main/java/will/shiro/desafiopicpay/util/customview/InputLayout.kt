package will.shiro.desafiopicpay.util.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import will.shiro.desafiopicpay.databinding.CustomInputLayoutBinding

class InputLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    val textInputEditText: TextInputEditText by lazy {
        binding.textInputEditText
    }
    val textInputLayout: TextInputLayout by lazy {
        binding.textInputLayout
    }
    private val binding: CustomInputLayoutBinding = CustomInputLayoutBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setHintText(hint: String) {
        binding.textInputLayout.hint = hint
    }
}