package app.mistercooper.domain.common.arch.extensions

import app.mistercooper.domain.common.arch.model.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch

fun <T> Flow<T>.catchFailure(action: suspend FlowCollector<T>.(cause: Throwable) -> Unit): Flow<T> =
    this.catch {
        if (it is Failure) throw it else action(it) }