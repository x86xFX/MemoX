package me.theek.memox.feature.camera.components

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.icons.Camera
import me.theek.memox.core.design_system.icons.CameraSwitch
import me.theek.memox.core.design_system.icons.Photo_library
import me.theek.memox.core.design_system.icons.Smartphone_camera
import me.theek.memox.feature.camera.R

@Composable
internal fun CameraView(
    context: Context,
    onPhotoPicks: (List<Uri>) -> Unit,
    onNavigateToBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cameraController = LifecycleCameraController(context).apply {
        setEnabledUseCases(LifecycleCameraController.IMAGE_CAPTURE)
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {
            onPhotoPicks(it)
            onNavigateToBack()
        }
    )

    Box(modifier = modifier) {
        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            controller = cameraController
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onNavigateToBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.navigate_back)
                    )
                }

                IconButton(
                    onClick = {
                        cameraController.cameraSelector =
                            if (cameraController.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            } else {
                                CameraSelector.DEFAULT_BACK_CAMERA
                            }
                    }
                ) {
                    Icon(
                        imageVector = CameraSwitch,
                        contentDescription = stringResource(R.string.change_camera)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Photo_library,
                        contentDescription = stringResource(R.string.access_gallery)
                    )
                }

                IconButton(
                    modifier = Modifier.size(50.dp),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Camera,
                        contentDescription = stringResource(R.string.take_a_picture)
                    )
                }

                IconButton(
                    onClick = {
                        photoPickerLauncher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                ) {
                    Icon(
                        imageVector = Smartphone_camera,
                        contentDescription = stringResource(R.string.access_gallery)
                    )
                }
            }
        }
    }
}