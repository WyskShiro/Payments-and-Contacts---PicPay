package will.shiro.data.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

data class RealmCreditCard(
    @PrimaryKey
    var id: Long = 0,
    val number: String = "",
    val ownerName: String = "",
    val expirationDate: Date = Date(),
    val cvv: Int = 0
) : RealmObject()