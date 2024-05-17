package app.mistercooper.ui.home.model

import app.mistercooper.domain.home.model.PostModel


data class HomeUiModel(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val postModels: List<PostModel>? = null
)