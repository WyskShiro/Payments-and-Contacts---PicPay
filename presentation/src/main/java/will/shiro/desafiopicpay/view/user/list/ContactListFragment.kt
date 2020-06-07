package will.shiro.desafiopicpay.view.user.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentContactListBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import will.shiro.desafiopicpay.util.error.Placeholder
import will.shiro.desafiopicpay.util.extensions.navigateSafe
import will.shiro.desafiopicpay.util.extensions.observeAction
import will.shiro.desafiopicpay.util.extensions.observeEvent
import will.shiro.desafiopicpay.view.MainViewModel
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.User
import javax.inject.Inject

class ContactListFragment : BaseFragment(R.layout.fragment_contact_list) {

    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<ContactListFragmentViewModel>
    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ContactListFragmentViewModel::class.java)
    }

    protected val activityViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentContactListBinding
    private val contactAdapter by lazy {
        ContactAdapter(viewModel::onContactSelected)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        binding = FragmentContactListBinding.bind(view)
        setupRecyclerView()
        setupRefreshRecycler()
        setupUi()
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observeAction(viewLifecycleOwner, ::onPlaceholder)
            contacts.observeAction(viewLifecycleOwner, ::onContacts)
            searchedContacts.observeAction(viewLifecycleOwner, ::onContacts)
            goToPrimingCreditCard.observeEvent(viewLifecycleOwner, ::onGoToPrimingCreditCard)
            goToPaymentCreditCard.observeEvent(viewLifecycleOwner, ::onGoToPaymentCreditCard)
        }
        activityViewModel.paymentSuccess.observeEvent(viewLifecycleOwner, ::goToReceiptCreditCard)
    }

    private fun setupRecyclerView() {
        with(binding.contactListRecyclerView) {
            if (adapter == null) {
                adapter = contactAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun setupRefreshRecycler() {
        with(binding) {
            contactListSwipeRefresh.setOnRefreshListener {
                contactListSwipeRefresh.isRefreshing = false
                searchContactsView.clear()
                viewModel.refresh()
            }
        }
    }

    private fun setupUi() {
        binding.searchContactsView.observeChanges(viewModel::onSearchText)
    }

    private fun onContacts(contacts: List<User>?) {
        contacts?.run(contactAdapter::updateList)
    }

    private fun onGoToPrimingCreditCard(user: User?) {
        user?.let {
            findNavController().navigateSafe(
                ContactListFragmentDirections.actionUserListFragmentToPrimingCreditCardFragment(it)
            )
        }
    }

    private fun onGoToPaymentCreditCard(userWithCreditCard: Pair<User, CreditCard>?) {
        userWithCreditCard?.run {
            findNavController().navigateSafe(
                ContactListFragmentDirections.actionUserListFragmentToPaymentCreditCardFragment(
                    first,
                    second
                )
            )
        }
    }

    // TODO on Resume -> if this.transaction != null -> execute this method
    // TODO this.transaction = transaction (in payment)
    private fun goToReceiptCreditCard(transaction: Transaction?) {
        transaction?.let {
            findNavController().navigateSafe(ContactListFragmentDirections
                    .actionUserListFragmentToReceiptCreditCardFragment(it)
            )
        }
    }

    private fun onPlaceholder(placeholder: Placeholder?) {
        placeholder?.let {
            binding.showLoading = it.visible()
        }
    }
}