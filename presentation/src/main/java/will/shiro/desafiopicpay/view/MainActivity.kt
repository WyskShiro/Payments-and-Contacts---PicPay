package will.shiro.desafiopicpay.view

import android.os.Bundle
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.util.base.BaseActivity
import will.shiro.desafiopicpay.util.base.BaseViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
