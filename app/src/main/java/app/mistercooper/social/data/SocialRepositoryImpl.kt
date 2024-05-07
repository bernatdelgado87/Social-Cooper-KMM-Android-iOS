package app.mistercooper.social.data

import app.mistercooper.social.data.remote.api.CooperAuthenticatedApi
import app.mistercooper.social.domain.common.GlobalFailure
import app.mistercooper.social.domain.feature.comment.model.CommentModel
import app.mistercooper.social.domain.feature.comment.model.CommentWrapperModel
import app.mistercooper.social.domain.feature.home.mapper.toModel
import app.mistercooper.social.domain.feature.home.model.FeedModel
import app.mistercooper.social.domain.feature.home.model.UserModel
import app.mistercooper.social.domain.repository.SocialRepository
import kotlinx.coroutines.delay
import java.io.File
import java.util.Date
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(private val apiRemote: CooperAuthenticatedApi) :
    SocialRepository {
    override suspend fun getFeed(): FeedModel {
        try {
            val response = apiRemote.getSocialFeed()
            if (response.isSuccessful) {
                response.body()?.let {
                    return it.toModel()
                }
            }
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }

    override suspend fun publishComment(
        comment: String,
        postId: Long,
        commentReferentId: Int?
    ): CommentWrapperModel {
        return mockCommentResponse()
    }

    override suspend fun getComments(postId: Long): CommentWrapperModel {
        return mockCommentResponse()
    }

    override suspend fun publishPost(text: String, file: File) {
        try {
            val response = apiRemote.publishPost(text, file)
            if (response.isSuccessful) {
                response.body()?.let {
                    return
                }
            }
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }

    }

    private suspend fun mockCommentResponse(): CommentWrapperModel {
        delay(5000)
        return CommentWrapperModel(
            "https://fastly.picsum.photos/id/40/4106/2806.jpg?hmac=MY3ra98ut044LaWPEKwZowgydHZ_rZZUuOHrc3mL5mI",
            listOf(
                CommentModel("muy bien", Date(), UserModel(1, "Pedro", "https://picsum.photos/id/237/200/300")),
                CommentModel("Good", Date(), UserModel(1, "vonderb", "https://picsum.photos/id/238/200/300")),
                CommentModel(
                    "Enhorabuena soy un texto muy largo lorem ipsum lorem ipsum patatas a la vinagreta más largo todavía d elo que puedas pensar por ser más largo soy mas largo que lo largo que llega a ser el cerebro de un gran pensador como yo que pienso en lo largo que puede llegar a ser el comentario de una persona que escribe libros en sus comentarios",
                    Date(),
                    UserModel(1,"manuekl", "https://picsum.photos/id/137/200/300"),
                ),
                CommentModel("los comentarios son mucho y muy comentarios", Date(), UserModel(1,"jaimito", "https://picsum.photos/id/247/200/300")),
                CommentModel("soy el siguiente cmoentario", Date(), UserModel(1, "mariel", "https://picsum.photos/id/238/200/300")),
                CommentModel("ahora voy yo", Date(), UserModel(1,"walter", "https://picsum.photos/id/217/200/300")),
                CommentModel("emoji \uD83E\uDD2C", Date(), UserModel(1,"patricio", "https://picsum.photos/id/287/200/300")),
            )
        )
    }
}