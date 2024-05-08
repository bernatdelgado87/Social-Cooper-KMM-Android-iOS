package app.mistercooper.social.data

import app.mistercooper.social.data.local.LocalUserDataSource
import app.mistercooper.social.data.remote.api.CooperNotAuthenticatedApi
import app.mistercooper.social.data.remote.dto.mapper.toDTO
import app.mistercooper.social.domain.common.GlobalFailure
import app.mistercooper.social.domain.feature.user.model.LoginUserModel
import app.mistercooper.social.domain.feature.user.model.RegisterUserModel
import app.mistercooper.social.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiRemote: CooperNotAuthenticatedApi,
    private val localUserDataSource: LocalUserDataSource
) : UserRepository {
    override suspend fun registerUser(registerUserModel: RegisterUserModel) {
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

    override fun isUserRegistered(): Boolean {
        return localUserDataSource.getApiKey()?.isNotEmpty()  == true
    }

    override fun getApiKey() = localUserDataSource.getApiKey()

    override suspend fun login(loginUserModel: LoginUserModel) {
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