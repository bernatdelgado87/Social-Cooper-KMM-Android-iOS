package app.mistercooper.domain.home.usecase

import app.mistercooper.domain.common.arch.usecase.None
import app.mistercooper.domain.common.arch.usecase.UseCase
import app.mistercooper.domain.home.model.FeedModel
import app.mistercooper.domain.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(private val socialRepository: HomeRepository): UseCase<FeedModel, None>() {
    override fun run(params: None): Flow<FeedModel> {
        return flow { emit(socialRepository.getFeed()) }
    }
}