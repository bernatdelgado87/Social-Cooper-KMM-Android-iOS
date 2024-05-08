package app.mistercooper.social.ui.feature.home.mock

import app.mistercooper.social.domain.feature.home.model.PostModel
import app.mistercooper.social.domain.feature.home.model.UserModel

fun mockPosts() = listOf(
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        12,
        true
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        0,
        false
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        1,
        true
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        40000,
        false
    )
)