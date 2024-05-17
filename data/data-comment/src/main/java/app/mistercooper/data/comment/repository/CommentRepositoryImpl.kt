package app.mistercooper.data.comment.repository

import app.mistercooper.data.comment.remote.api.CommentRemoteApi
import app.mistercooper.data.comment.remote.dto.mapper.toModel
import app.mistercooper.domain.comment.model.CommentWrapperModel
import app.mistercooper.domain.comment.repository.CommentRepository
import app.mistercooper.domain.common.arch.model.GlobalFailure
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val apiRemote: CommentRemoteApi) :
    CommentRepository {

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

}