package will.shiro.desafiopicpay.util.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import will.shiro.desafiopicpay.databinding.CustomInputLayoutBinding

class InputLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    private val binding: CustomInputLayoutBinding = CustomInputLayoutBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setHintText(hint: String) {
        binding.textInputLayout.hint = hint
    }
}