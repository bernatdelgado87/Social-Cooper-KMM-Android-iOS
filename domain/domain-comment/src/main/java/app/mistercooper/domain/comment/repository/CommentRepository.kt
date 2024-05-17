package app.mistercooper.domain.comment.repository

interface CommentRepository {
    suspend fun publishComment(comment: String, postId: Long, commentReferentId: Int? = null): app.mistercooper.domain.comment.model.CommentWrapperModel
    suspend fun getComments(postId: Long): app.mistercooper.domain.comment.model.CommentWrapperModel
}