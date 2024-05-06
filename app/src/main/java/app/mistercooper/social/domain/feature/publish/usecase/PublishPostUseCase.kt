package app.mistercooper.social.domain.feature.publish.usecase

import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.repository.SocialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class PublishPostUseCase @Inject constructor(val socialRepository: SocialRepository) :
    UseCase<Unit, PublishPostUseCase.PublishPostParams>() {
    override fun run(params: PublishPostParams): Flow<Unit> {
        return flow { emit(socialRepository.publishPost(params.text, params.file)) }
    }

    data class PublishPostParams(val text: String, val file: File)
}