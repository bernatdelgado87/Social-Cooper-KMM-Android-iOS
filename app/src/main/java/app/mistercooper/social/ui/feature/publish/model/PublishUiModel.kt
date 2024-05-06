package app.mistercooper.social.ui.feature.publish.model

import android.net.Uri
import java.io.File

data class PublishUiModel(
    val loading: Boolean = false,
    val error: Boolean = false,
    val localImages: List<Uri>? = null,
    val postPublishedSuccess: Boolean = false
)
