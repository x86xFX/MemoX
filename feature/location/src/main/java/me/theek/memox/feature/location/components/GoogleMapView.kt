package me.theek.memox.feature.location.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import me.theek.memox.core.design_system.icons.My_location
import me.theek.memox.core.util.LocationState
import me.theek.memox.feature.location.R

@Composable
internal fun GoogleMapView(
    locationStream: LocationState,
    onCurrentLocationClick: () -> Unit,
    onSaveCurrentLocation: (latitude: Double, longitude: Double) -> Unit,
    onNavigateToBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isMarkerAdded by rememberSaveable { mutableStateOf(false) }
    var isMapLoaded by remember { mutableStateOf(false) }
    val cameraPosition = rememberCameraPositionState()
    val markerState = rememberMarkerState()

    Box(modifier = modifier.fillMaxSize()) {

        AnimatedVisibility(
            modifier = Modifier.matchParentSize(),
            visible = !isMapLoaded,
            exit = fadeOut()
        ) {
            Column (
                modifier = Modifier
                    .matchParentSize()
                    .background(MaterialTheme.colorScheme.background)
                    .navigationBarsPadding()
                    .statusBarsPadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(strokeCap = StrokeCap.Round)
                Text(text = "Please wait map is loading...")
            }
        }

        val padding = with(LocalDensity.current) {
            WindowInsets.navigationBars.getBottom(this).toDp()
        }


        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPosition,
            onMapLoaded = { isMapLoaded = true },
            contentPadding = PaddingValues(bottom = padding),
            properties = MapProperties(
                isBuildingEnabled = true,
                isMyLocationEnabled = false,
                mapType = MapType.HYBRID
            ),
            uiSettings = MapUiSettings(
                compassEnabled = true,
                zoomControlsEnabled = false
            )
        ) {
            if (isMarkerAdded) {
                Marker(
                    state = markerState,
                    draggable = true,
                    contentDescription = null
                )
            }
        }

        if (isMapLoaded) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .statusBarsPadding()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 45.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedIconButton (onClick = onCurrentLocationClick) {
                    Icon(
                        imageVector = My_location,
                        contentDescription = stringResource(R.string.current_location)
                    )
                }

                AnimatedVisibility(visible = isMarkerAdded) {
                    Button (
                        onClick = {
                            onSaveCurrentLocation(markerState.position.latitude, markerState.position.longitude)
                            onNavigateToBack()
                        }
                    ) {
                        Text(text = "Save")
                    }
                }

                FilledTonalButton (
                    onClick = {
                        isMarkerAdded = !isMarkerAdded
                        if (isMarkerAdded) { markerState.position = cameraPosition.position.target }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = stringResource(R.string.add_or_remove_marker)
                    )
                    Text(text = if (isMarkerAdded) "Remove marker" else "Add marker")
                }
            }
        }
    }

    when (locationStream){
        is LocationState.Failure, LocationState.Idle -> Unit
        is LocationState.Success -> {
            LaunchedEffect(key1 = locationStream) {
                locationStream.data?.let {
                    cameraPosition.animate(
                        update = CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 16f),
                        durationMs = 1000
                    )
                }
            }
        }
    }
}