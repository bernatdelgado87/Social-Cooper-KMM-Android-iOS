package app.mistercooper.social.ui.home.model

import app.mistercooper.social.domain.feature.home.model.PostModel

data class HomeUiModel(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val postModels: List<PostModel>? = null
)