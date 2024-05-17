package app.mistercooper.ui.registerlogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.ui.common.utils.BuildConfigFieldsProvider
import app.mistercooper.domain.register_login.model.LoginUserModel
import app.mistercooper.domain.register_login.model.RegisterUserModel
import app.mistercooper.domain.register_login.usecase.LoginUseCase
import app.mistercooper.domain.register_login.usecase.RegisterUserUseCase
import app.mistercooper.ui.registerlogin.model.RegisterLoginUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RegisterLoginViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase,
    internal val buildConfigFieldsProvider: BuildConfigFieldsProvider
    ) : ViewModel() {

    private val _registerLoginUiModelState = MutableStateFlow(
        RegisterLoginUiModel()
    )
    val registerLoginState = _registerLoginUiModelState.asStateFlow()

    fun registerUser(email: String, userName: String, password: String, file: File) {
        viewModelScope.launch {
            registerUserUseCase(
                RegisterUserModel(
                    name = userName,
                    email = email,
                    password = password,
                    imageProfile = file
                )
            )
                .catch {
                    it.printStackTrace()
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(error = true))

                }.collect { response ->
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(registerLoginSuccess = true))
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(
                LoginUserModel(
                    email = email,
                    password = password
                )
            )
                .catch {
                    it.printStackTrace()
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(error = true))

                }.collect { response ->
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(registerLoginSuccess = true))
                }
        }
    }


}