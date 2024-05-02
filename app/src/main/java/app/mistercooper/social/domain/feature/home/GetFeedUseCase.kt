package app.mistercooper.social.domain.feature.home

import app.mistercooper.social.domain.common.None
import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.feature.home.model.FeedModel
import app.mistercooper.social.domain.repository.SocialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(val socialRepository: SocialRepository): UseCase<FeedModel, None>() {
    override fun run(params: None): Flow<FeedModel> {
        return flow { emit(socialRepository.getFeed()) }
    }
}