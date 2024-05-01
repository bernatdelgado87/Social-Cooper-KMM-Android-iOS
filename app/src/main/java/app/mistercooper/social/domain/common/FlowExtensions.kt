package app.mistercooper.social.domain.common

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch

fun <T> Flow<T>.catchFailure(action: suspend FlowCollector<T>.(cause: Throwable) -> Unit): Flow<T> =
    this.catch {
        if (it is Failure) throw it else action(it) }