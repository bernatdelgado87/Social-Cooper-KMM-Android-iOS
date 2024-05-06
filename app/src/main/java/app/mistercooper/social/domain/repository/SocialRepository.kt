package app.mistercooper.social.domain.repository

import app.mistercooper.social.domain.feature.comment.model.CommentModel
import app.mistercooper.social.domain.feature.home.model.FeedModel
import java.io.File

interface SocialRepository {
    suspend fun getFeed(): FeedModel
    suspend fun publishComment(comment: String, postId: Long, commentReferentId: Int? = null): List<CommentModel>
    suspend fun getComments(postId: Long): List<CommentModel>
    suspend fun publishPost(text: String, file: File)
}