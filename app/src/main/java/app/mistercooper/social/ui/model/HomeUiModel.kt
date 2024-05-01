package app.mistercooper.social.ui.model

import app.mistercooper.social.domain.feature.home.model.Post

data class HomeUiModel(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val posts: List<Post>? = null
)