package will.shiro.desafiopicpay.util.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import will.shiro.desafiopicpay.util.error.DialogData
import will.shiro.desafiopicpay.util.extensions.observeEvent
import will.shiro.desafiopicpay.util.extensions.showDialog
import javax.inject.Inject

abstract class BaseBottomSheetDialog : BottomSheetDialogFragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private var dialogData: Dialog? = null

    abstract val baseViewModel: BaseViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any?>? {
        return androidInjector
    }

    @CallSuper
    open fun subscribeUi() {
        baseViewModel.toast.observeEvent(viewLifecycleOwner, ::onNextToast)
        baseViewModel.dialog.observeEvent(viewLifecycleOwner, ::onGetDialog)
    }

    open fun onGetDialog(dialogData: DialogData?) {
        dialogData?.let {
            dialog?.dismiss()
            this.dialogData = activity?.showDialog(it)
        }
    }

    protected fun popFragment() {
        findNavController().popBackStack()
    }

    private fun onNextToast(text: String?) {
        text?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}