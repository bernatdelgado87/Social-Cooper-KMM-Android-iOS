package app.mistercooper.social.domain.repository

import app.mistercooper.social.domain.feature.comment.model.CommentModel
import app.mistercooper.social.domain.feature.comment.model.CommentWrapperModel
import app.mistercooper.social.domain.feature.home.model.FeedModel
import java.io.File

interface SocialRepository {
    suspend fun getFeed(): FeedModel
    suspend fun publishComment(comment: String, postId: Long, commentReferentId: Int? = null): CommentWrapperModel
    suspend fun getComments(postId: Long): CommentWrapperModel
    suspend fun publishPost(text: String, file: File)
}