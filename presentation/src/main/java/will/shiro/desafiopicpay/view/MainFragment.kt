package will.shiro.desafiopicpay.view

import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.base.BaseViewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {
    override val baseViewModel: BaseViewModel
        get() = viewModel

    lateinit var viewModel: MainFragmentViewModel
}