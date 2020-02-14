package will.shiro.desafiopicpay.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.ActivityMainBinding
import will.shiro.desafiopicpay.util.base.BaseActivity
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.di.ViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
