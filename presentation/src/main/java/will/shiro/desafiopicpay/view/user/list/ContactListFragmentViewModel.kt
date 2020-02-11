package will.shiro.desafiopicpay.view.user.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.rxkotlin.subscribeBy
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.extensions.defaultPlaceholders
import will.shiro.desafiopicpay.util.extensions.defaultSched
import will.shiro.desafiopicpay.util.scheduler.SchedulerProvider
import will.shiro.domain.entity.User
import will.shiro.domain.interactor.user.GetUsers
import javax.inject.Inject

class ContactListFragmentViewModel @AssistedInject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getUsers: GetUsers
) : BaseViewModel() {

    val users: LiveData<List<User>> get() = _users

    private val _users by lazy { MutableLiveData<List<User>>() }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        getUsers()
    }

    private fun getUsers() {
        getUsers.execute()
            .defaultPlaceholders(::setPlaceholder)
            .defaultSched(schedulerProvider)
            .subscribeBy({ setDialog(it, ::getUsers) }) {
                _users.value = it
            }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(): ContactListFragmentViewModel
    }
}