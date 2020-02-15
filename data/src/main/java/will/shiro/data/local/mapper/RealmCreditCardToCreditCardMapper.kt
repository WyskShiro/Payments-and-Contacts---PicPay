package will.shiro.data.local.mapper

import will.shiro.data.local.entity.RealmCreditCard
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.entity.CreditCard
import javax.inject.Inject

class RealmCreditCardToCreditCardMapper @Inject constructor() :
    Mapper<RealmCreditCard, CreditCard>() {

    override fun transform(t: RealmCreditCard): CreditCard {
        return CreditCard(
            number = t.number,
            ownerName = t.ownerName,
            expirationDate = t.expirationDate,
            cvv = t.cvv
        )
    }
}