package will.shiro.desafiopicpay.util.base

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import will.shiro.desafiopicpay.util.error.DialogData
import will.shiro.desafiopicpay.util.extensions.observeEvent
import will.shiro.desafiopicpay.util.extensions.showDialog

abstract class BaseFragment : DaggerFragment() {

    private var dialog: Dialog? = null

    abstract val baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    open fun subscribeUi() {
        baseViewModel.toast.observeEvent(viewLifecycleOwner, ::onNextToast)
        baseViewModel.dialog.observeEvent(viewLifecycleOwner, ::onGetDialog)
    }

    open fun onGetDialog(dialogData: DialogData?) {
        dialogData?.let {
            dialog?.dismiss()
            dialog = activity?.showDialog(it)
        }
    }

    private fun onNextToast(text: String?) {
        text?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}