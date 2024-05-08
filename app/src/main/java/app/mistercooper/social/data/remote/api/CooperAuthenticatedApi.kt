package app.mistercooper.social.data.remote.api

import app.mistercooper.social.data.remote.dto.mapper.generateMultiPartBody
import app.mistercooper.social.data.remote.dto.request.PublishCommentBodyDTO
import app.mistercooper.social.data.remote.dto.response.CommentResponseDTO
import app.mistercooper.social.data.remote.dto.response.MultimediaFeedDTO
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.io.File
import javax.inject.Inject


class CooperAuthenticatedApi @Inject constructor(private val service: Service) {

    suspend fun getSocialFeed(): Response<MultimediaFeedDTO> = service.getFeed()
    suspend fun publishPost(text: String, file: File): Response<MultimediaFeedDTO> {
        val requestBody = generateMultiPartBody(text, file)
        return service.publishPost(requestBody)
    }

    suspend fun getComments(postId: Long, n: Int, offset: Long): Response<CommentResponseDTO> = service.getComments(postId, n, offset)
    suspend fun publishComment(postId: Long, text: String): Response<CommentResponseDTO> = service.publishComment(PublishCommentBodyDTO(postId, text))

    interface Service {
        @GET("feed")
        suspend fun getFeed(): Response<MultimediaFeedDTO>

        @GET("detail")
        suspend fun getComments(
            @Query("postId") postId: Long,
            @Query("n") n:Int,
            @Query("offset") offset: Long
            ): Response<CommentResponseDTO>

        @POST("comment")
        suspend fun publishComment(
            @Body body: PublishCommentBodyDTO
        ): Response<CommentResponseDTO>

        @POST("upload")
        suspend fun publishPost(
            @Body body: RequestBody
        ): Response<MultimediaFeedDTO>

    }

    companion object {
        const val SOCIAL_API_URL = "http://192.168.1.235:8080/social/"
    }
}