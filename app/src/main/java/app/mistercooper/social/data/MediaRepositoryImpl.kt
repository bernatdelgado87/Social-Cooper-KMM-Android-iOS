package app.mistercooper.social.data

import android.content.Context
import android.net.Uri
import app.mistercooper.social.data.local.LocalDataSource
import app.mistercooper.social.domain.repository.MediaRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.io.File

class MediaRepositoryImpl(@ApplicationContext private val context: Context): MediaRepository {
    private val localDataSource = LocalDataSource(context)

    override suspend fun getSavedImages() = localDataSource.fetchSavedImages().map { it.uri }.toList()
    override suspend fun getSavedImageByUri(uri: Uri): File = localDataSource.getImageFromUri(uri)

}

