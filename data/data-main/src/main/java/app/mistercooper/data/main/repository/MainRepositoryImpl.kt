package app.mistercooper.data.home.repository

import app.mistercooper.data.common.local.LocalUserDataSource
import app.mistercooper.domain.common.feature.user.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val localUserDataSource: LocalUserDataSource):
    MainRepository {
    override fun isUserRegistered(): Boolean =
        localUserDataSource.getApiKey()?.isNotEmpty() == true

}