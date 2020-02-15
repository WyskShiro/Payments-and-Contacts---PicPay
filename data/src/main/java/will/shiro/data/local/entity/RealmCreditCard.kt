package will.shiro.data.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmCreditCard(
    @PrimaryKey
    var id: Long = 0,
    var number: String = "",
    var ownerName: String = "",
    var expirationDate: Date = Date(),
    var cvv: Int = 0
) : RealmObject()