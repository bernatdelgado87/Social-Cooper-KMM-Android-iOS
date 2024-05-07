package app.mistercooper.social.ui.feature.registerlogin.model

import android.net.Uri
import java.io.File

data class RegisterLoginUiModel(
    val loading: Boolean = false,
    val error: Boolean = false,
    val registerLoginSuccess: Boolean = false
)
