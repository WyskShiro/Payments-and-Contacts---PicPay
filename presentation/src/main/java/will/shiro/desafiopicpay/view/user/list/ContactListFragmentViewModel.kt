package will.shiro.desafiopicpay.view.user.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxkotlin.subscribeBy
import will.shiro.domain.util.throwable.NoItemFoundLocalThrowable
import will.shiro.desafiopicpay.util.base.BaseViewModel
import will.shiro.desafiopicpay.util.base.Event
import will.shiro.desafiopicpay.util.extensions.defaultPlaceholders
import will.shiro.desafiopicpay.util.extensions.defaultSched
import will.shiro.desafiopicpay.util.scheduler.SchedulerProvider
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.entity.User
import will.shiro.domain.interactor.creditcard.GetCreditCard
import will.shiro.domain.interactor.user.GetUsers
import javax.inject.Inject

class ContactListFragmentViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getUsers: GetUsers,
    private val getCreditCard: GetCreditCard
) : BaseViewModel() {

    val contacts: LiveData<List<User>> get() = _contacts
    val searchedContacts: LiveData<List<User>> get() = _searchedContacts
    val goToPrimingCreditCard: LiveData<Event<User>> get() = _goToPrimingCreditCard
    val goToPaymentCreditCard: LiveData<Event<Pair<User, CreditCard>>> get() = _goToPaymentCreditCard

    private val _contacts by lazy { MutableLiveData<List<User>>() }
    private val _searchedContacts by lazy { MutableLiveData<List<User>>() }
    private val _goToPrimingCreditCard by lazy { MutableLiveData<Event<User>>() }
    private val _goToPaymentCreditCard by lazy { MutableLiveData<Event<Pair<User, CreditCard>>>() }

    init {
        getUsers()
    }

    fun onSearchText(text: String) {
        _contacts.value?.run {
            _searchedContacts.value =
                filter { it.name.contains(text) || it.username.contains(text) }
        }
    }

    fun onContactSelected(user: User) {
        getCreditCard.execute()
            .defaultSched(schedulerProvider)
            .defaultPlaceholders(::setPlaceholder)
            .subscribeBy({ onContactSelectedFailure(it, user) }) {
                _goToPaymentCreditCard.value = Event(user to it)
            }
    }

    fun refresh() {
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

    private fun onContactSelectedFailure(throwable: Throwable, user: User) {
        when (throwable) {
            is NoItemFoundLocalThrowable -> {
                _goToPrimingCreditCard.value = Event(user)
            }
            else -> setDialog(throwable, {
                onContactSelected(user)
            })
        }
    }
}