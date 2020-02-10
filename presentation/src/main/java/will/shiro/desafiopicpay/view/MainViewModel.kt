package will.shiro.desafiopicpay.view

import com.squareup.inject.assisted.AssistedInject
import will.shiro.desafiopicpay.util.base.BaseViewModel

class MainViewModel @AssistedInject constructor() : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(): MainViewModel
    }
}