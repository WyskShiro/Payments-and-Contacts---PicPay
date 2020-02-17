package will.shiro.desafiopicpay.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import setClick
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.databinding.ActivityMainBinding
import will.shiro.desafiopicpay.util.base.BaseActivity
import will.shiro.desafiopicpay.util.base.BaseViewModel

class MainActivity : BaseActivity() {
    override val baseViewModel: BaseViewModel get() = viewModel

    protected val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNavController()
    }

    private fun setupNavController() {
        val navController = findNavController(R.id.main_navigation_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.backImageView.isVisible = when (destination.id) {
                R.id.userListFragment, R.id.receiptCreditCardFragment -> false
                else -> true
            }
        }
        binding.backImageView.setClick {
            navController.popBackStack()
        }
    }
}
