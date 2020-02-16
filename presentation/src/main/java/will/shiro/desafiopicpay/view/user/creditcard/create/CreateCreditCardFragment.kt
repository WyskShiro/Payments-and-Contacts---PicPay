package will.shiro.desafiopicpay.view.user.creditcard.create

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import observeChanges
import setVisible
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentCreateCreditCardBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import will.shiro.desafiopicpay.util.extensions.observeAction
import will.shiro.desafiopicpay.util.mask.CreditCardMask
import will.shiro.desafiopicpay.util.mask.DateMonthYearMask
import will.shiro.domain.util.form.CreditCardFormFields
import javax.inject.Inject

class CreateCreditCardFragment : BaseFragment(R.layout.fragment_create_credit_card) {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<CreateCreditCardViewModel>
    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CreateCreditCardViewModel::class.java)
    }

    private lateinit var binding: FragmentCreateCreditCardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = FragmentCreateCreditCardBinding.bind(view)
        setupUi()
    }

    private fun setupUi() {
        with(binding) {
            numberInput.textInputEditText.observeChanges {
                viewModel.onInputTextChanged(it, CreditCardFormFields.NUMBER)
            }
            CreditCardMask.apply(binding.numberInput.textInputEditText)
            ownerNameInput.textInputEditText.observeChanges {
                viewModel.onInputTextChanged(it, CreditCardFormFields.OWNER_NAME)
            }
            expirationDateInput.textInputEditText.observeChanges {
                viewModel.onInputTextChanged(it, CreditCardFormFields.EXPIRATION_DATE)
            }
            DateMonthYearMask.apply(binding.expirationDateInput.textInputEditText)
            cvvInput.textInputEditText.observeChanges {
                viewModel.onInputTextChanged(it, CreditCardFormFields.CVV)
            }
        }
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.shouldEnableSave.observeAction(viewLifecycleOwner, ::onShouldEnableSave)
    }

    private fun onShouldEnableSave(shouldEnableSave: Boolean?) {
        shouldEnableSave?.let {
            binding.createButton.setVisible(it)
            if (binding.expirationDateInput.textInputEditText.hasFocus() || binding.cvvInput.textInputEditText.hasFocus()) {
                binding.scrollView.smoothScrollTo(0, binding.createButton.bottom)
            }
        }
    }
}