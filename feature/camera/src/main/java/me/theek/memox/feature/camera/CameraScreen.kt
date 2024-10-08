package me.theek.memox.feature.camera

import android.Manifest
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import me.theek.memox.core.design_system.components.PermissionSettingUi
import me.theek.memox.core.util.PermissionState
import me.theek.memox.feature.camera.components.CameraView

@Composable
fun CameraScreen(
    permissionState: PermissionState,
    onCameraPermissionCheck: () -> Unit,
    onPhotosPick: (List<Uri>) -> Unit,
    onBackPress: () -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                onCameraPermissionCheck()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    when (permissionState) {
        PermissionState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(strokeCap = StrokeCap.Round)
            }
        }
        PermissionState.PermissionGranted -> {
            CameraView(
                modifier = Modifier.fillMaxSize(),
                context = context,
                onPhotoPicks = onPhotosPick,
                onNavigateToBack = onBackPress
            )
        }
        PermissionState.PermissionDenied -> {
            var shouldShowPermissionSettingsUi by rememberSaveable { mutableStateOf(false) }
            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { shouldShowPermissionSettingsUi = it.not() }
            )

            LaunchedEffect(key1 = Unit) {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }

            if (shouldShowPermissionSettingsUi) {
                PermissionSettingUi(
                    context = context,
                    description = R.string.grant_camera_permission_in_order_to_add_photos_to_the_notes
                )
            }
        }
    }

    BackHandler(onBack = onBackPress)
}