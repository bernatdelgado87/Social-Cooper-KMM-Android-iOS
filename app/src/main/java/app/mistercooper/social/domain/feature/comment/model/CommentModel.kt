package app.mistercooper.social.domain.feature.comment.model

import app.mistercooper.social.domain.feature.home.model.UserModel
import java.util.Date

data class CommentModel(val text: String,
                   val date: Date,
                   val user: UserModel,
                   val relatedComment: CommentModel? = null)