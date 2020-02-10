package will.shiro.desafiopicpay.util.extensions

import io.reactivex.Single
import will.shiro.desafiopicpay.util.error.Placeholder

fun <T> Single<T>.defaultSched(schedulerProvider: SchedulerProvider): Single<T> {
    return this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.main())
}

fun <T> Single<T>.defaultConsumers(
    onSubscribeCallback: () -> (Unit),
    doAfterTerminatecallback: () -> (Unit)
): Single<T> {
    return this.doOnSubscribe { onSubscribeCallback.invoke() }
        .doAfterTerminate { doAfterTerminatecallback.invoke() }
}

fun <T> Single<T>.defaultPlaceholders(placeholderPlacerAction: (Placeholder) -> (Unit)): Single<T> {
    return this.defaultConsumers(
        { placeholderPlacerAction(Placeholder.Loading()) },
        { placeholderPlacerAction(Placeholder.HideAll) })
}