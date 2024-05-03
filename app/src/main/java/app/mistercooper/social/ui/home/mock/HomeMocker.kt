package app.mistercooper.social.ui.home.mock

import app.mistercooper.social.domain.feature.home.model.Comment
import app.mistercooper.social.domain.feature.home.model.Post
import app.mistercooper.social.domain.feature.home.model.UserModel
import java.util.Date

fun mockPosts() = listOf(
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        12
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        0
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        1
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        40000
    )
)