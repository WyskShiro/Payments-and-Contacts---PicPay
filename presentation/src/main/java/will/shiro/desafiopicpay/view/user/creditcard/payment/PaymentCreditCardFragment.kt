package will.shiro.desafiopicpay.view.user.creditcard.payment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentPaymentCreditCardBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import javax.inject.Inject

class PaymentCreditCardFragment : BaseFragment(R.layout.fragment_payment_credit_card) {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<PaymentCreditCardViewModel>
    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PaymentCreditCardViewModel::class.java)
    }

    private lateinit var binding: FragmentPaymentCreditCardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentCreditCardBinding.bind(view)
    }
}