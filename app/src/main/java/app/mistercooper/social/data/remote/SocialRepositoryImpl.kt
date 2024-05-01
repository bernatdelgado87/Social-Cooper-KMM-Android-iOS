package app.mistercooper.social.data.remote

import app.mistercooper.social.data.remote.api.CooperApi
import app.mistercooper.social.domain.common.Failure
import app.mistercooper.social.domain.common.GlobalFailure
import app.mistercooper.social.domain.feature.home.mapper.toModel
import app.mistercooper.social.domain.feature.home.model.FeedModel
import app.mistercooper.social.domain.repository.SocialRepository
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(private val apiRemote: CooperApi) :
    SocialRepository {
    override suspend fun getFeed(): FeedModel {
        try {
            val response = apiRemote.getSocialFeed()
            if (response.isSuccessful) {
                response.body()?.let {
                    return it.toModel()
                }
            }
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }

    }
}