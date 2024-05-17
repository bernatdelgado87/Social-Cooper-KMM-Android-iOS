package app.mistercooper.data.comment.remote.api

import app.mistercooper.data.comment.remote.dto.request.PublishCommentBodyDTO
import app.mistercooper.data.comment.remote.dto.response.CommentResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject

class CommentRemoteApi @Inject constructor(private val service: Service) {
    suspend fun getComments(postId: Long, n: Int, offset: Long): Response<CommentResponseDTO> = service.getComments(postId, n, offset)
    suspend fun publishComment(postId: Long, text: String): Response<CommentResponseDTO> = service.publishComment(
        PublishCommentBodyDTO(postId, text)
    )

    interface Service {
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

    }
}