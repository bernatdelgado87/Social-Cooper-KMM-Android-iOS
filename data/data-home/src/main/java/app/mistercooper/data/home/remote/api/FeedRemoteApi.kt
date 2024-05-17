package app.mistercooper.data.home.remote.api

import app.mistercooper.data.home.remote.dto.response.MultimediaFeedDTO
import app.mistercooper.data.home.remote.dto.request.PublishLikeBodyDTO
import app.mistercooper.data.home.remote.dto.response.LikeResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject

class FeedRemoteApi @Inject constructor(private val service: Service) {
    suspend fun getSocialFeed(): Response<MultimediaFeedDTO> = service.getFeed()
    suspend fun publishLike(postId: Long, like: Boolean): Response<LikeResponseDTO> = service.publishLike(PublishLikeBodyDTO(postId, like))

    interface Service {
        @GET("feed")
        suspend fun getFeed(): Response<MultimediaFeedDTO>

        @POST("like")
        suspend fun publishLike(
            @Body body: PublishLikeBodyDTO
        ): Response<LikeResponseDTO>

    }
}