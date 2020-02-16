package will.shiro.domain.util.form.validation

class EmptyValidation(
    override val isRequired: Boolean = true
) : BaseValidation {

    override var field: String? = null

    override fun isValid(): Boolean {
        return field?.run { isNotBlank() } ?: false
    }
}