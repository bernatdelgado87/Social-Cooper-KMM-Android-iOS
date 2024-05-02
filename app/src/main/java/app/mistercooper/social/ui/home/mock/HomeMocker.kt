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
        mockCommments()
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        emptyList()
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        mockCommments()
    ),
    Post(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        mockCommments()
    )
)

fun mockCommments() = listOf(Comment("Lorem ipsum Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    Date(),
    UserModel(1, "Kevin Martin"),
    ))