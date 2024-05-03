package app.mistercooper.social.ui.comment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.feature.comment.GetCommentsUseCase
import app.mistercooper.social.domain.feature.comment.PublishCommentUseCase
import app.mistercooper.social.ui.comment.model.PublishCommentUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val publishCommentUseCase: PublishCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
) : ViewModel() {
    private val _commentUiModelState = MutableStateFlow(PublishCommentUiModel())
    val commentUiModel = _commentUiModelState.asStateFlow()

    fun publishComment(comment: String, postId: Long, commentReferentId: Int? = null) {
        viewModelScope.launch {
            publishCommentUseCase(
                PublishCommentUseCase.PublishCommentParams(comment, postId, commentReferentId)
            )
                .onStart {
                    _commentUiModelState.emit(commentUiModel.value.copy(isError = false, isLoadingPublish = true))
                }
                .catch {
                    it.printStackTrace()
                    _commentUiModelState.emit(commentUiModel.value.copy(isError = true, isLoadingPublish = false))

                }.collect { response ->
                    _commentUiModelState.emit(PublishCommentUiModel(comments = response))
                }
        }
    }

    fun getComments(postId: Long) {
        viewModelScope.launch {
            getCommentsUseCase(
                GetCommentsUseCase.GetCommentsParams(postId)
            )
                .onStart {
                    _commentUiModelState.emit(commentUiModel.value.copy(isError = false, isLoadingComments = true))
                }
                .catch {
                    it.printStackTrace()
                    _commentUiModelState.emit(commentUiModel.value.copy(isError = true, isLoadingComments = false))

                }.collect { response ->
                    _commentUiModelState.emit(PublishCommentUiModel(comments = response))
                }
        }
    }

}