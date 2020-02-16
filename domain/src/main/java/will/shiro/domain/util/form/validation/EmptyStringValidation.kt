package will.shiro.domain.util.form.validation

import will.shiro.domain.util.extension.ONLY_LETTERS_PATTERN

class EmptyStringValidation(
    override val isRequired: Boolean = true
) : BaseValidation {

    override var field: String? = null

    override fun isValid(): Boolean {
        return field?.run { isNotBlank() && matches(Regex(ONLY_LETTERS_PATTERN)) } ?: false
    }
}