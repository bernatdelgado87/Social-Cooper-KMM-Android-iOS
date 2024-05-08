package app.mistercooper.social.ui.common.viewModel

import android.net.Uri

data class MediaUiModel(
    val error: Boolean = false,
    val localImages: List<Uri>? = null,
)
