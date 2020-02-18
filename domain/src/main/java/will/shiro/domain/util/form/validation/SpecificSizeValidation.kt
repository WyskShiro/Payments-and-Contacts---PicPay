package will.shiro.domain.util.form.validation

class SpecificSizeValidation(
    override val isRequired: Boolean = true,
    private val minFieldSize: Int
) : BaseValidation {

    override var field: String? = null

    override fun isValid(): Boolean {
        return field?.run { length == minFieldSize } ?: false
    }
}