package app.mistercooper.domain.common.feature.media.usecase

import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import app.mistercooper.domain.common.arch.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class GetImageFromUriUseCase @Inject constructor(private val mediaRepository: MediaRepository) :
    UseCase<File, String>() {
    override fun run(params: String): Flow<File> {
        return flow {
            emit(mediaRepository.getSavedImageByUri(params))
        }
    }
}