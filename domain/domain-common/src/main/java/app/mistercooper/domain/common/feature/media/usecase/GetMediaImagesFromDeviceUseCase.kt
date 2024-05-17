package app.mistercooper.domain.common.feature.media.usecase

import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import app.mistercooper.domain.common.arch.usecase.None
import app.mistercooper.domain.common.arch.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMediaImagesFromDeviceUseCase @Inject constructor(private val mediaRepository: MediaRepository): UseCase<Unit, None>() {
    override fun run(params: None): Flow<Unit> {
        return flow {
            emit(mediaRepository.getSavedImages())
        }
    }
}