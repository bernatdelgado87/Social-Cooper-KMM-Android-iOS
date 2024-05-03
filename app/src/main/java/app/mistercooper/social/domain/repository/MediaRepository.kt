package app.mistercooper.social.domain.repository

import android.net.Uri
import java.io.File

interface MediaRepository {
    suspend fun getSavedImages(): List<Uri>

    suspend fun getSavedImageByUri(uri: Uri): File
}