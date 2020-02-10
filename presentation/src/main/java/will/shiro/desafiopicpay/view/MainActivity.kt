package will.shiro.desafiopicpay.view

import android.os.Bundle
import assistedViewModels
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.util.base.BaseActivity
import will.shiro.desafiopicpay.util.base.BaseViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    lateinit var homeFactory: MainViewModel.Factory

    private val viewModel by assistedViewModels {
        homeFactory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
