package will.shiro.data.local.mapper

import will.shiro.data.local.entity.RealmCreditCard
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.entity.CreditCard
import javax.inject.Inject

class CreditCardToRealmCreditCardMapper @Inject constructor() :
    Mapper<CreditCard, RealmCreditCard>() {

    override fun transform(t: CreditCard): RealmCreditCard {
        return RealmCreditCard(
            number = t.number,
            ownerName = t.ownerName,
            expirationDate = t.expirationDate,
            cvv = t.cvv
        )
    }
}