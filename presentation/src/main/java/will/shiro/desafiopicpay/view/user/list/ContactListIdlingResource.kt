package will.shiro.desafiopicpay.view.user.list

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val coutingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        coutingIdlingResource.increment()
    }

    fun decrement() {
        if (!coutingIdlingResource.isIdleNow) {
            coutingIdlingResource.decrement()
        }
    }
}