package will.shiro.desafiopicpay.view.user.creditcard.payment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import observeChanges
import setClick
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentPaymentCreditCardBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import will.shiro.desafiopicpay.util.error.Placeholder
import will.shiro.desafiopicpay.util.extensions.observeAction
import will.shiro.desafiopicpay.util.extensions.openKeyboard
import will.shiro.desafiopicpay.view.MainViewModel
import will.shiro.domain.entity.Transaction
import javax.inject.Inject

class PaymentCreditCardFragment : BaseFragment(R.layout.fragment_payment_credit_card) {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<PaymentCreditCardViewModel>
    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PaymentCreditCardViewModel::class.java)
    }

    protected val activityViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentPaymentCreditCardBinding
    val args: PaymentCreditCardFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentCreditCardBinding.bind(view)
        setupUi()
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.shouldEnablePay.observeAction(viewLifecycleOwner, ::onShouldEnablePay)
        viewModel.placeholder.observeAction(viewLifecycleOwner, ::onPlaceholder)
        viewModel.paymentSuccess.observeAction(viewLifecycleOwner, ::onPaymentSuccess)
    }

    private fun setupUi() {
        with(binding) {
            moneyInputLayout.editText.observeChanges(viewModel::onMoneyValueChanged)
            user = args.user
            creditCard = args.creditCard
            createButton.setClick(viewModel::onPay)
            openKeyboard(moneyInputLayout.editText)
        }
    }

    private fun onShouldEnablePay(shouldEnable: Boolean?) {
        shouldEnable?.let {
            binding.createButton.isEnabled = it
        }
    }

    private fun onPlaceholder(placeholder: Placeholder?) {
        placeholder?.let {
            binding.showLoading = it.visible()
        }
    }

    private fun onPaymentSuccess(transaction: Transaction?) {
        transaction?.let {
            activityViewModel.onPaymentSuccess(it)
            findNavController().popBackStack()
        }
    }
}