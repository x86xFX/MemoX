package me.theek.memox.feature.location

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import me.theek.memox.core.design_system.components.PermissionSettingUi
import me.theek.memox.core.model.LocationDetails
import me.theek.memox.core.util.LocationState
import me.theek.memox.core.util.PermissionState
import me.theek.memox.feature.location.components.GoogleMapView

@Composable
fun LocationScreen(
    locationStream: LocationState,
    currentLocation: LocationDetails?,
    permissionState: PermissionState,
    onLocationPermissionCheck: () -> Unit,
    onCurrentLocationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                onLocationPermissionCheck()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    when (permissionState) {
        PermissionState.Loading -> {}
        PermissionState.PermissionGranted -> {
            GoogleMapView(
                modifier = modifier,
                locationStream = locationStream,
                onCurrentLocationClick = onCurrentLocationClick
            )
        }
        PermissionState.PermissionDenied -> {
            var shouldShowPermissionSettingsUi by rememberSaveable { mutableStateOf(false) }
            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = {
                    shouldShowPermissionSettingsUi = !it.containsValue(true)
                }
            )

            LaunchedEffect(key1 = Unit) {
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                    )
                )
            }

            if (shouldShowPermissionSettingsUi) {
                PermissionSettingUi(
                    context = context,
                    description = R.string.grant_location_permission_in_order_to_get_current_location_to_the_map
                )
            }
        }
    }
}