package app.mistercooper.social.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import app.mistercooper.social.ui.theme.SocialCooperAndroidTheme

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    showKeyboard: Boolean = false,
    placeholderText: String = "",
    singleLine: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize
) {

    var text by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    if (showKeyboard) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }


    BasicTextField(modifier = modifier
        .background(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.shapes.small,
        )
        .focusRequester(focusRequester),
        value = text,
        onValueChange = {
            text = it
            onTextChanged(it)
        },
        singleLine = singleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    SocialCooperAndroidTheme {
        CustomTextField(
            modifier = Modifier.height(40.dp),
            { },
            placeholderText = "Escribe algo aquí"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLargeTextFieldPreview() {
    SocialCooperAndroidTheme {
        CustomTextField(
            modifier = Modifier.height(40.dp),
            { },
            placeholderText = "Escribe algo aquí",
            singleLine = false
        )
    }
}