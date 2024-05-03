package app.mistercooper.social.ui.common.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.core.content.ContextCompat

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return density * value
}

@Composable
fun requestCameraPermission(onPermissionGranted: () -> Unit, onPermissionDenied: () -> Unit): ManagedActivityResultLauncher<String, Boolean> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            onPermissionGranted()
        } else {
           onPermissionDenied()
        }
    }
}

fun Context.checkCustomPermission(onPermissionGranted: () -> Unit, onPermissionDenied: () -> Unit) {
    val permissionCheckResult =
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        onPermissionGranted()
    } else {
        onPermissionDenied()
    }
}