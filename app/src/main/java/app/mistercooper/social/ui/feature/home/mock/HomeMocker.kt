package app.mistercooper.social.ui.feature.home.mock

import app.mistercooper.social.domain.feature.home.model.PostModel
import app.mistercooper.social.domain.feature.home.model.UserModel

fun mockPosts() = listOf(
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        12
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        0
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        1
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        40000
    )
)