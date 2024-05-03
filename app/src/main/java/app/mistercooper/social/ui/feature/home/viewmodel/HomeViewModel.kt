package app.mistercooper.social.ui.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.feature.home.usecase.GetFeedUseCase
import app.mistercooper.social.ui.feature.home.model.HomeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
) : ViewModel() {
    private val _homeuiModelState = MutableStateFlow(
        HomeUiModel()
    )
    val homeUiModel = _homeuiModelState.asStateFlow()

    init {
        getFeed()
    }

    fun getFeed() {
        viewModelScope.launch {
            getFeedUseCase()
                .onStart {
                    _homeuiModelState.emit(HomeUiModel(isLoading = true))
                }
                .catch {
                    it.printStackTrace()
                    _homeuiModelState.emit(HomeUiModel(isError = true))

                }.collect { response ->
                    _homeuiModelState.emit(HomeUiModel(postModels = response.postModels))
                }
        }
    }

}