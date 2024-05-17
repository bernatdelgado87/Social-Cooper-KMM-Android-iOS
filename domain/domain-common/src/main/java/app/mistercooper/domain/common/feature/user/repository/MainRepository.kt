package app.mistercooper.domain.common.feature.user.repository

interface MainRepository {
    fun isUserRegistered(): Boolean
}