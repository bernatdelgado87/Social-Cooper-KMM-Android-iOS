package app.mistercooper.domain.register_login.repository

import app.mistercooper.domain.register_login.model.LoginUserModel
import app.mistercooper.domain.register_login.model.RegisterUserModel

interface UserRepository {
    suspend fun registerUser(registerUserModel: app.mistercooper.domain.register_login.model.RegisterUserModel)
    suspend fun login(loginUserModel: app.mistercooper.domain.register_login.model.LoginUserModel)

    fun getApiKey(): String?

}