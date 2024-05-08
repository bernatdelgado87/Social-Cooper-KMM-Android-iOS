package app.mistercooper.social.domain.feature.home.usecase

import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.repository.SocialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PublishLikeUseCase @Inject constructor(private val socialRepository: SocialRepository): UseCase<Unit, PublishLikeUseCase.PublishLikeParams>() {
    override fun run(params: PublishLikeParams): Flow<Unit> {
        return flow { emit(socialRepository.publishLike(params.postId, params.like)) }
    }

    data class PublishLikeParams(val postId: Long, val like: Boolean)
}