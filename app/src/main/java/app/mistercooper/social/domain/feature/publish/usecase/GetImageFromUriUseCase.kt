package app.mistercooper.social.domain.feature.publish.usecase

import android.net.Uri
import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class GetImageFromUriUseCase @Inject constructor(private val mediaRepository: MediaRepository): UseCase<File, Uri>() {
    override fun run(params: Uri): Flow<File> {
        return flow {
            emit(mediaRepository.getSavedImageByUri(params))
        }
    }
}