package app.mistercooper.social.domain.feature.publish.usecase

import android.net.Uri
import app.mistercooper.social.domain.common.None
import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMediaImagesFromDeviceUseCase @Inject constructor(private val mediaRepository: MediaRepository): UseCase<List<Uri>, None>() {
    override fun run(params: None): Flow<List<Uri>> {
        return flow {
            emit(mediaRepository.getSavedImages())
        }
    }
}