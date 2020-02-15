package will.shiro.desafiopicpay.view.user.creditcard.create

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import setClick
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentPrimingCreditCardBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import javax.inject.Inject

class PrimingCreditCardFragment : BaseFragment(R.layout.fragment_priming_credit_card) {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<PrimingCreditCardViewModel>
    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PrimingCreditCardViewModel::class.java)
    }

    private lateinit var binding: FragmentPrimingCreditCardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrimingCreditCardBinding.bind(view)
        setupUi()
    }

    private fun setupUi() {
        binding.backImageView.setClick(::popFragment)
    }
}