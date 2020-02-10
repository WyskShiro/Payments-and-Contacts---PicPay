package will.shiro.desafiopicpay.util.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import will.shiro.desafiopicpay.util.base.Event
import will.shiro.desafiopicpay.util.base.EventObserver

fun <T> LiveData<T>.observeAction(owner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(owner, androidx.lifecycle.Observer { observer(it) })
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(owner, EventObserver(observer))
}