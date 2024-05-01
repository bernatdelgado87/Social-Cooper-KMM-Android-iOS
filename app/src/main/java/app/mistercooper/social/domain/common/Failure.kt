package app.mistercooper.social.domain.common

abstract class Failure : Throwable()

sealed class GlobalFailure : Failure() {
    data class GlobalError(val throwable: Throwable? = null) : GlobalFailure()
}
