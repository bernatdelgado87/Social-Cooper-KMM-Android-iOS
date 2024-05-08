package app.mistercooper.social.data

import app.mistercooper.social.data.remote.api.CooperAuthenticatedApi
import app.mistercooper.social.domain.common.GlobalFailure
import app.mistercooper.social.domain.feature.comment.model.CommentWrapperModel
import app.mistercooper.social.domain.feature.home.mapper.toModel
import app.mistercooper.social.domain.feature.home.model.FeedModel
import app.mistercooper.social.domain.feature.mapper.toModel
import app.mistercooper.social.domain.repository.SocialRepository
import java.io.File
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(private val apiRemote: CooperAuthenticatedApi) :
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

    override suspend fun publishComment(
        comment: String,
        postId: Long,
        commentReferentId: Int?
    ): CommentWrapperModel {
        try {
            val response = apiRemote.publishComment(
                postId = postId,
                text = comment
            )
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

    override suspend fun getComments(postId: Long): CommentWrapperModel {
        try {
            val response = apiRemote.getComments(
                postId = postId,
                n = 20,
                offset = 0
            )
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

    override suspend fun publishPost(text: String, file: File) {
        try {
            val response = apiRemote.publishPost(text, file)
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

    override suspend fun publishLike(postId: Long, like: Boolean) {
        try {
            val response = apiRemote.publishLike(
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