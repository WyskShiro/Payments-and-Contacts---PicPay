package will.shiro.desafiopicpay.view.user.creditcard.create

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import setClick
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentCreateCreditCardBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
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
        binding = FragmentCreateCreditCardBinding.bind(view)
        setupUi()
    }

    private fun setupUi() {
    }
}