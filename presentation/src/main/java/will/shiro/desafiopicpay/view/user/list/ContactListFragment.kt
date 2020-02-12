package will.shiro.desafiopicpay.view.user.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import assistedViewModels
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.FragmentContactListBinding
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.error.Placeholder
import will.shiro.desafiopicpay.util.extensions.observeAction
import will.shiro.domain.entity.User
import javax.inject.Inject

class ContactListFragment : BaseFragment(R.layout.fragment_contact_list) {

    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    lateinit var homeFactory: ContactListFragmentViewModel.Factory

    private val viewModel: ContactListFragmentViewModel by assistedViewModels {
        homeFactory.create()
    }
    private lateinit var binding: FragmentContactListBinding
    private val contactAdapter = ContactAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        binding = FragmentContactListBinding.bind(view)
        setupRecyclerView()
        setupUi()
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.placeholder.observeAction(viewLifecycleOwner, ::onPlaceholder)
        viewModel.contacts.observeAction(viewLifecycleOwner, ::onContacts)
        viewModel.searchedContacts.observeAction(viewLifecycleOwner, ::onContacts)
    }

    private fun setupRecyclerView() {
        with(binding.contactListRecyclerView) {
            if (adapter == null) {
                adapter = contactAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun setupUi() {
        binding.searchContactsView.observeChanges(viewModel::onSearchText)
    }

    private fun onContacts(contacts: List<User>?) {
        contacts?.run(contactAdapter::updateList)
    }

    private fun onPlaceholder(placeholder: Placeholder?) {
        placeholder?.let {
            binding.showLoading = it.visible()
        }
    }
}