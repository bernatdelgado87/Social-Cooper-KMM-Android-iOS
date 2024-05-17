package app.mistercooper.data.register_login.repository

import app.mistercooper.data.common.local.LocalUserDataSource
import app.mistercooper.data.register_login.remote.api.RegisterLoginApi
import app.mistercooper.data.register_login.remote.mapper.toDTO
import app.mistercooper.domain.common.arch.model.GlobalFailure
import app.mistercooper.domain.register_login.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiRemote: RegisterLoginApi,
    private val localUserDataSource: LocalUserDataSource
) : UserRepository {
    override suspend fun registerUser(registerUserModel: app.mistercooper.domain.register_login.model.RegisterUserModel) {
        try {
            val response = apiRemote.registerUser(
                name = registerUserModel.name,
                email = registerUserModel.email,
                password = registerUserModel.password,
                imageProfile = registerUserModel.imageProfile
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    localUserDataSource.saveApiKey(it.apikey)
                    return
                }
            }
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }

    override fun getApiKey() = localUserDataSource.getApiKey()

    override suspend fun login(loginUserModel: app.mistercooper.domain.register_login.model.LoginUserModel) {
        try {
            val response = apiRemote.login(loginUserModel.toDTO())
            if (response.isSuccessful) {
                response.body()?.let {
                    localUserDataSource.saveApiKey(it.apikey)
                    return
                }
            }
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }    }

}