package app.mistercooper.domain.home.usecase

import app.mistercooper.domain.common.arch.usecase.UseCase
import app.mistercooper.domain.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PublishLikeUseCase @Inject constructor(private val socialRepository: HomeRepository): UseCase<Unit, PublishLikeUseCase.PublishLikeParams>() {
    override fun run(params: PublishLikeParams): Flow<Unit> {
        return flow { emit(socialRepository.publishLike(params.postId, params.like)) }
    }

    data class PublishLikeParams(val postId: Long, val like: Boolean)
}