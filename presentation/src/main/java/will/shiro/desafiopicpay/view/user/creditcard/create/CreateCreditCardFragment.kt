package will.shiro.desafiopicpay.view.user.creditcard.create

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import observeChanges
import setClick
import setVisible
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentCreateCreditCardBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import will.shiro.desafiopicpay.util.extensions.navigateSafe
import will.shiro.desafiopicpay.util.extensions.observeAction
import will.shiro.desafiopicpay.util.mask.CVVEditConfigurations
import will.shiro.desafiopicpay.util.mask.CreditCardEditConfigurations
import will.shiro.desafiopicpay.util.mask.DateMonthYearEditConfigurations
import will.shiro.domain.entity.CreditCard
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
    private val args: CreateCreditCardFragmentArgs by navArgs()

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
            CreditCardEditConfigurations.apply(numberInput.textInputEditText)
            ownerNameInput.textInputEditText.observeChanges {
                viewModel.onInputTextChanged(it, CreditCardFormFields.OWNER_NAME)
            }
            expirationDateInput.textInputEditText.observeChanges {
                viewModel.onInputTextChanged(it, CreditCardFormFields.EXPIRATION_DATE)
            }
            DateMonthYearEditConfigurations.apply(expirationDateInput.textInputEditText)
            cvvInput.textInputEditText.observeChanges {
                viewModel.onInputTextChanged(it, CreditCardFormFields.CVV)
            }
            CVVEditConfigurations.apply(cvvInput.textInputEditText)
            createButton.setClick(viewModel::saveCreditCard)
        }
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.shouldEnableSave.observeAction(viewLifecycleOwner, ::onShouldEnableSave)
        viewModel.goToPaymentCreditCard.observeAction(viewLifecycleOwner, ::onGoToPaymentCreditCard)
    }

    private fun onShouldEnableSave(shouldEnableSave: Boolean?) {
        shouldEnableSave?.let { _shouldEnableSave ->
            with(binding) {
                createButton.setVisible(_shouldEnableSave)
                if (shouldScrollToBottom()) {
                    scrollView.smoothScrollTo(0, createButton.bottom)
                }
            }
        }
    }

    private fun onGoToPaymentCreditCard(creditCard: CreditCard?) {
        creditCard?.let {
            findNavController().navigateSafe(CreateCreditCardFragmentDirections
                .actionCreateCreditCardFragmentToPaymentCreditCardFragment(
                    args.user,
                    it
                )
            )
        }
    }

    private fun shouldScrollToBottom(): Boolean {
        return binding.expirationDateInput.textInputEditText.hasFocus() ||
                binding.cvvInput.textInputEditText.hasFocus()
    }
}