package will.shiro.desafiopicpay.view.user.list

import androidx.lifecycle.*
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.rxkotlin.subscribeBy
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.extensions.defaultPlaceholders
import will.shiro.desafiopicpay.util.extensions.defaultSched
import will.shiro.desafiopicpay.util.scheduler.SchedulerProvider
import will.shiro.domain.entity.User
import will.shiro.domain.interactor.user.GetUsers

class ContactListFragmentViewModel @AssistedInject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getUsers: GetUsers
) : BaseViewModel() {

    val users: LiveData<List<User>> get() = _users
    val searchedUsers: LiveData<List<User>> get() = _searchedUsers

    private val _users by lazy { MutableLiveData<List<User>>() }
    private var _searchedUsers = MutableLiveData<List<User>>()

    fun onSearchText(text: String) {
        _users.value?.run {
            _searchedUsers.value = filter { it.name.contains(text) || it.username.contains(text) }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        getUsers()
    }

    private fun getUsers() {
        getUsers.execute()
            .defaultPlaceholders(::setPlaceholder)
            .defaultSched(schedulerProvider)
            .subscribeBy(::onGetUsersFailure) {
                _users.value = it
            }
    }

    private fun onGetUsersFailure(throwable: Throwable) {
        _users.value = listOf()
        setDialog(throwable, ::getUsers)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(): ContactListFragmentViewModel
    }
}