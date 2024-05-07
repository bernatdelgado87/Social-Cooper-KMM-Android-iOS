package app.mistercooper.social.data

import android.content.Context
import android.net.Uri
import app.mistercooper.social.data.local.LocalMediaDataSource
import app.mistercooper.social.domain.repository.MediaRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.io.File

class MediaRepositoryImpl(@ApplicationContext private val context: Context): MediaRepository {
    private val localMediaDataSource = LocalMediaDataSource(context)

    override suspend fun getSavedImages() = localMediaDataSource.fetchSavedImages().map { it.uri }.toList()
    override suspend fun getSavedImageByUri(uri: Uri): File = localMediaDataSource.getImageFromUri(uri)

}

