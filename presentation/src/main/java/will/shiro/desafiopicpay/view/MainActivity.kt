package will.shiro.desafiopicpay.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import assistedViewModels
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.ActivityMainBinding
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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
