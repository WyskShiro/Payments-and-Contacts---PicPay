package will.shiro.desafiopicpay.view.user.creditcard.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentReceiptCreditCardBinding
import will.shiro.desafiopicpay.util.base.BaseBottomSheetDialog
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import will.shiro.desafiopicpay.util.extensions.observeAction
import will.shiro.domain.entity.CreditCard
import javax.inject.Inject

class ReceiptCreditCardFragment : BaseBottomSheetDialog() {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<ReceiptCreditCardViewModel>
    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ReceiptCreditCardViewModel::class.java)
    }

    private lateinit var binding: FragmentReceiptCreditCardBinding
    val args: ReceiptCreditCardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiptCreditCardBinding.inflate(inflater, container, false)
        subscribeUi()
        setupUi()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val behavior = BottomSheetBehavior.from(view?.parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.creditCard.observeAction(viewLifecycleOwner, ::onCreditCard)
    }

    private fun setupUi() {
        binding.transaction = args.transaction
    }

    private fun onCreditCard(creditCard: CreditCard?) {
        creditCard?.let {
            binding.creditCard = it
        }
    }
}