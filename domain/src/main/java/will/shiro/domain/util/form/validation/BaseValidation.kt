package will.shiro.domain.util.form.validation

interface BaseValidation {

    val isRequired: Boolean
    var field: String?

    fun isValid(): Boolean
}