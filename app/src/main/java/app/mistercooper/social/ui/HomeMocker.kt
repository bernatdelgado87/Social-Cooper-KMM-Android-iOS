package app.mistercooper.social.ui

import app.mistercooper.social.domain.feature.home.model.Post
import app.mistercooper.social.domain.feature.home.model.UserModel

fun mockPosts() = listOf(
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12
    )
)