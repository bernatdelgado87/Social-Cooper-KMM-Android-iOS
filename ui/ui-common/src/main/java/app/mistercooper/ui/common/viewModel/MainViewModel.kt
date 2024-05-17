package app.mistercooper.ui.common.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.domain.common.feature.user.usecase.IsUserRegisteredUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isUserRegisteredUseCase: IsUserRegisteredUseCase
) : ViewModel() {
    private val _isRegisteredState: MutableStateFlow<Boolean?> = MutableStateFlow(
        null
    )
    val isRgisteredState = _isRegisteredState.asStateFlow()

    init {
        isRegistered()
    }

    fun isRegistered() {
        viewModelScope.launch {
            isUserRegisteredUseCase()
                .catch {
                    it.printStackTrace()
                }.collect { response ->
                    _isRegisteredState.emit(response)
                }
        }
    }

}