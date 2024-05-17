package app.mistercooper.domain.comment.usecase

import app.mistercooper.domain.comment.model.CommentWrapperModel
import app.mistercooper.domain.comment.repository.CommentRepository
import app.mistercooper.domain.common.arch.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(val socialRepository: CommentRepository): UseCase<CommentWrapperModel, GetCommentsUseCase.GetCommentsParams>() {
    override fun run(params: GetCommentsParams): Flow<CommentWrapperModel> {
        return flow { emit(socialRepository.getComments(params.postId)) }
    }

    data class GetCommentsParams(val postId: Long)
}