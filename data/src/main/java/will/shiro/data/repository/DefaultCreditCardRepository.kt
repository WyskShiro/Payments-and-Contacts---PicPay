package will.shiro.data.repository

import io.reactivex.Single
import io.realm.Realm
import io.realm.kotlin.where
import will.shiro.data.local.entity.RealmCreditCard
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.util.throwable.NoItemFoundLocalThrowable
import will.shiro.domain.boundary.CreditCardRepository
import will.shiro.domain.entity.CreditCard
import javax.inject.Inject

class DefaultCreditCardRepository @Inject constructor(
    private val realmCreditCardMapper: Mapper<RealmCreditCard, CreditCard>,
    private val creditCardMapper: Mapper<CreditCard, RealmCreditCard>
) : CreditCardRepository {

    override fun getOne(): Single<CreditCard> {
        return Single.create { _emitter ->
            val creditCard = Realm.getDefaultInstance().where<RealmCreditCard>().findFirst()
            creditCard?.let {
                _emitter.onSuccess(realmCreditCardMapper.transform(it))
            } ?: run {
                _emitter.onError(NoItemFoundLocalThrowable())
            }
        }
    }

    override fun addOrUpdate(creditCard: CreditCard): Single<CreditCard> {
        return Single.create<CreditCard> { _emitter ->
            Realm.getDefaultInstance().executeTransaction { _realm ->
                _realm.copyToRealmOrUpdate(creditCardMapper.transform(creditCard))
                _realm.where<RealmCreditCard>().findFirst()?.let {
                    _emitter.onSuccess(realmCreditCardMapper.transform(it))
                }
            }
        }
    }
}