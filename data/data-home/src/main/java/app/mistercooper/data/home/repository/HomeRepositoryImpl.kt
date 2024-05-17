package app.mistercooper.data.home.repository

import app.mistercooper.data.home.remote.api.FeedRemoteApi
import app.mistercooper.data.home.remote.dto.mapper.toModel
import app.mistercooper.domain.common.arch.model.GlobalFailure
import app.mistercooper.domain.home.model.FeedModel
import app.mistercooper.domain.home.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val feedRemoteApi: FeedRemoteApi): HomeRepository {
    override suspend fun getFeed(): FeedModel {
        try {
            val response = feedRemoteApi.getSocialFeed()
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

    override suspend fun publishLike(postId: Long, like: Boolean) {
        try {
            val response = feedRemoteApi.publishLike(
                postId = postId,
                like = like
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    return
                }
            }
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }
}