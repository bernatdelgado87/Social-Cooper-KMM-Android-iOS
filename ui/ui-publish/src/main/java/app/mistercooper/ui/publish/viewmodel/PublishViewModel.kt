package app.mistercooper.ui.publish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.ui.common.utils.BuildConfigFieldsProvider
import app.mistercooper.social.domain.feature.publish.usecase.PublishPostUseCase
import app.mistercooper.ui.publish.model.PublishUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    private val publishPostUseCase: PublishPostUseCase,
    internal val buildConfigFieldsProvider: BuildConfigFieldsProvider,
    ) : ViewModel() {
    private val _publishUiModelState = MutableStateFlow(
        PublishUiModel()
    )
    val publishUiModelState = _publishUiModelState.asStateFlow()

    fun publishPost(text: String, file: File){
            viewModelScope.launch {
                publishPostUseCase(PublishPostUseCase.PublishPostParams(text, file))
                    .onStart {
                        _publishUiModelState.emit(PublishUiModel(loading = true))
                    }
                    .catch {
                        it.printStackTrace()
                        _publishUiModelState.emit(PublishUiModel(error = true))
                    }.collect { response ->
                        _publishUiModelState.emit(PublishUiModel(postPublishedSuccess = true))
                    }
            }
        }
}