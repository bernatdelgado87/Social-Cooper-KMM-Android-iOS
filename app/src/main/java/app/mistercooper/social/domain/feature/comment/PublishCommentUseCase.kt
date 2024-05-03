package app.mistercooper.social.domain.feature.comment

import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.feature.comment.model.CommentModel
import app.mistercooper.social.domain.repository.SocialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PublishCommentUseCase @Inject constructor(val socialRepository: SocialRepository): UseCase<List<CommentModel>, PublishCommentUseCase.PublishCommentParams>() {
    override fun run(params: PublishCommentParams): Flow<List<CommentModel>> {
        return flow { emit(socialRepository.publishComment(params.comment, params.postId, params.commentReferentId)) }
    }

    data class PublishCommentParams(val comment: String, val postId: Long, val commentReferentId: Int? = null)
}