package app.mistercooper.social.ui.feature.registerlogin

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.mistercooper.social.R
import app.mistercooper.social.ui.common.components.CommonScaffoldTopBar
import app.mistercooper.social.ui.common.components.CustomTextField
import app.mistercooper.social.ui.common.components.LoadingComponent
import app.mistercooper.social.ui.common.components.SelectSourceBottomSheet
import app.mistercooper.social.ui.common.components.TextType
import app.mistercooper.social.ui.common.utils.findActivity
import app.mistercooper.social.ui.common.viewModel.MediaViewModel
import app.mistercooper.social.ui.feature.main.MainActivity
import app.mistercooper.social.ui.feature.registerlogin.viewmodel.RegisterLoginViewModel
import coil.compose.AsyncImage

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel = hiltViewModel<RegisterLoginViewModel>()
    val state = viewModel.registerLoginState.collectAsState()
    val mediaViewModel = hiltViewModel<MediaViewModel>()
    val selectedFileState = mediaViewModel.selectedFile.collectAsState()

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var canActivateButton by remember { mutableStateOf(false) }

    canActivateButton = email.isNotEmpty() && password.isNotEmpty() && selectedFileState.value != null

    if (state.value.registerLoginSuccess) {
        //todo find a better solution for reset activity
        val context = LocalContext.current
        context.startActivity(Intent(context, MainActivity::class.java))
        context.findActivity()?.finish()
    }

    CommonScaffoldTopBar(
        navController = navController,
        topBarTitle = stringResource(id = R.string.register_toolbar_title),
        content = { modifier ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = stringResource(R.string.registration_registrate_now_title),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    SelectMediaComponent()
                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            userName = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(id = R.string.register_name),
                    )

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            email = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(id = R.string.register_email),
                        type = TextType.EMAIL
                    )

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            password = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(id = R.string.register_password),
                        type = TextType.PASSWORD
                    )
                    Button(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        enabled = canActivateButton,
                        onClick = {
                            selectedFileState.value?.let {
                                viewModel.registerUser(
                                    email = email,
                                    userName = userName,
                                    password = password,
                                    file = selectedFileState.value!!
                                )
                            }
                        }) {
                        Text(text = stringResource(R.string.register_button))
                    }
                    if (state.value.loading) {
                        LoadingComponent()
                    }
                }
            }
        },
        showError = state.value.error
    )
}

@Composable
fun SelectMediaComponent() {
    val mediaViewModel = hiltViewModel<MediaViewModel>()
    var showSheetSourceSelection by remember { mutableStateOf(false) }

    if (showSheetSourceSelection) {
        SelectSourceBottomSheet {
            showSheetSourceSelection = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxHeight(0.2f)
            .padding(20.dp)
            .background(color = Color.Transparent),
    ) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .border(2.dp, Color.Gray, CircleShape)
                    .fillMaxHeight()
                    .clickable { showSheetSourceSelection = true },
                model = mediaViewModel.selectedFile.collectAsState().value?: R.drawable.img_placeholder_profile,
                contentDescription = stringResource(R.string.your_photo_content_description),
            )
    }
}
