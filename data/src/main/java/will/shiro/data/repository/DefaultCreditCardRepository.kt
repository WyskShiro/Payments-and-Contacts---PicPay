package will.shiro.data.repository

import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.realm.Realm
import io.realm.kotlin.where
import will.shiro.data.local.entity.RealmCreditCard
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.boundary.CreditCardRepository
import will.shiro.domain.entity.CreditCard
import javax.inject.Inject

class DefaultCreditCardRepository @Inject constructor(
    private val realm: Realm,
    private val realmCreditCardMapper: Mapper<RealmCreditCard, CreditCard>,
    private val creditCardMapper: Mapper<CreditCard, RealmCreditCard>
): CreditCardRepository {

    override fun getOne(): Single<CreditCard> {
        return realm.where<RealmCreditCard>()
            .findFirstAsync()
            .asFlowable<RealmCreditCard>()
            .firstOrError()
            .map(realmCreditCardMapper::transform)
    }

    override fun addOrUpdate(creditCard: CreditCard): Single<CreditCard> {
        return Single.create<CreditCard> { _emitter ->
            realm.executeTransactionAsync({ _realm ->
                _realm.copyToRealmOrUpdate(creditCardMapper.transform(creditCard))
            }, {
                onAddOrUpdateSuccess(_emitter)
            }, {
                _emitter.onError(it)
            })
        }
    }

    private fun onAddOrUpdateSuccess(emitter: SingleEmitter<CreditCard>) {
        realm.where<RealmCreditCard>().findFirst()?.let {
            emitter.onSuccess(realmCreditCardMapper.transform(it))
        }
    }
}