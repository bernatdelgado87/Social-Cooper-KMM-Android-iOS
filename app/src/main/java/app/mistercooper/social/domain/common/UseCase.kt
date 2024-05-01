package app.mistercooper.social.domain.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<out T, in Params : Any>() {

    abstract fun run(params: Params): Flow<T>

    operator fun invoke(params: Params = None as Params) = run(params).flowOn(Dispatchers.IO)

}

object None
