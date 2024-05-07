package app.mistercooper.social.domain.repository

import app.mistercooper.social.domain.feature.user.model.LoginUserModel
import app.mistercooper.social.domain.feature.user.model.RegisterUserModel

interface UserRepository {
    suspend fun registerUser(registerUserModel: RegisterUserModel)
    suspend fun login(loginUserModel: LoginUserModel)

    fun isUserRegistered(): Boolean

    fun getApiKey(): String?

}