package will.shiro.desafiopicpay.util.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import observeChanges
import will.shiro.desafiopicpay.databinding.CustomSearchTextBinding
import will.shiro.desafiopicpay.util.extensions.setVisible

class SearchEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private val binding: CustomSearchTextBinding = CustomSearchTextBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        setupTextWatcher()
    }

    fun observeChanges(callback: (String) -> Unit) {
        binding.searchEditText.observeChanges(callback)
    }

    fun clear() {
        binding.searchEditText.setText("")
    }

    private fun setupTextWatcher() {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            text?.run {
                binding.apply {
                    hintTextView.setVisible(isEmpty())
                    searchIconImageView.setVisible(isNotEmpty())
                    searchEditText.isCursorVisible = isNotEmpty()
                }
            }
        }
    }
}