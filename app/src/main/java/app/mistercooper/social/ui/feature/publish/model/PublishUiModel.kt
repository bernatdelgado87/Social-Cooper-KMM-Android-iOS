package app.mistercooper.social.ui.feature.publish.model

import android.net.Uri
import java.io.File

data class PublishUiModel(
    val isLoadingLocalImages: Boolean = false,
    val errorOnLoadingLocalImages: Boolean = false,
    val localImages: List<Uri>? = null,
    val selectedPhoto: File? = null
)
