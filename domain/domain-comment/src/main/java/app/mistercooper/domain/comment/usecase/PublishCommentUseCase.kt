package app.mistercooper.domain.comment.usecase

import app.mistercooper.domain.comment.model.CommentWrapperModel
import app.mistercooper.domain.comment.repository.CommentRepository
import app.mistercooper.domain.common.arch.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PublishCommentUseCase @Inject constructor(val socialRepository: CommentRepository): UseCase<CommentWrapperModel, PublishCommentUseCase.PublishCommentParams>() {
    override fun run(params: PublishCommentParams): Flow<CommentWrapperModel> {
        return flow { emit(socialRepository.publishComment(params.comment, params.postId, params.commentReferentId)) }
    }

    data class PublishCommentParams(val comment: String, val postId: Long, val commentReferentId: Int? = null)
}