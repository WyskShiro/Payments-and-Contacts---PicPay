package will.shiro.desafiopicpay.view.user.list

import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel

class ContactListFragment : BaseFragment(R.layout.fragment_contact_list) {
    override val baseViewModel: BaseViewModel
        get() = viewModel

    lateinit var viewModel: ContactListFragmentViewModel
}