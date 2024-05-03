package app.mistercooper.social.data.remote

import app.mistercooper.social.data.remote.api.CooperApi
import app.mistercooper.social.domain.common.GlobalFailure
import app.mistercooper.social.domain.feature.comment.model.CommentModel
import app.mistercooper.social.domain.feature.home.mapper.toModel
import app.mistercooper.social.domain.feature.home.model.FeedModel
import app.mistercooper.social.domain.feature.home.model.UserModel
import app.mistercooper.social.domain.repository.SocialRepository
import kotlinx.coroutines.delay
import java.util.Date
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(private val apiRemote: CooperApi) :
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

    override suspend fun publishComment(comment: String, postId: Long, commentReferentId: Int?): List<CommentModel> {
        return mockCommentResponse()
    }

    override suspend fun getComments(postId: Long): List<CommentModel> {
        return mockCommentResponse()
    }

    private suspend fun mockCommentResponse(): List<CommentModel>{
        delay(5000)
        return listOf(
            CommentModel("muy bien", Date(), UserModel(1)),
            CommentModel("Good", Date(), UserModel(1)),
            CommentModel("Enhorabuena soy un texto muy largo lorem ipsum lorem ipsum patatas a la vinagreta más largo todavía d elo que puedas pensar por ser más largo soy mas largo que lo largo que llega a ser el cerebro de un gran pensador como yo que pienso en lo largo que puede llegar a ser el comentario de una persona que escribe libros en sus comentarios", Date(), UserModel(1)),
            CommentModel("los comentarios son mucho y muy comentarios", Date(), UserModel(1)),
            CommentModel("soy el siguiente cmoentario", Date(), UserModel(1)),
            CommentModel("ahora voy yo", Date(), UserModel(1)),
            CommentModel("emoji \uD83E\uDD2C", Date(), UserModel(1)),
        )
    }
}