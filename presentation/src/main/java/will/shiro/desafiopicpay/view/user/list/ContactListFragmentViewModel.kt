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

    val contacts: LiveData<List<User>> get() = _contacts
    val searchedContacts: LiveData<List<User>> get() = _searchedContacts

    private val _contacts by lazy { MutableLiveData<List<User>>() }
    private var _searchedContacts = MutableLiveData<List<User>>()

    fun onSearchText(text: String) {
        _contacts.value?.run {
            _searchedContacts.value = filter { it.name.contains(text) || it.username.contains(text) }
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
                _contacts.value = it
            }
    }

    private fun onGetUsersFailure(throwable: Throwable) {
        _contacts.value = listOf()
        setDialog(throwable, ::getUsers)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(): ContactListFragmentViewModel
    }
}